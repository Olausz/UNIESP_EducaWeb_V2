package com.example.API.domain.Matricula;

import com.example.API.domain.Disciplina.DisciplinaAlunoResponse;
import com.example.API.domain.Enums.StatusMatricula;
import com.example.API.infra.TratamentoDeExceções.TratamentoDeErros;
import com.example.API.domain.Aluno.Aluno;
import com.example.API.domain.Disciplina.Disciplina;
import com.example.API.domain.Aluno.AlunoRepository;
import com.example.API.domain.Disciplina.DisciplinaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaDoAlunoService {

    public static final double MEDIA_PARA_APROVAÇÃO = 7.0;

    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    public void criarMatricula(MatriculaDTO dto){
        Aluno aluno = alunoRepository.findById(dto.alunoId())
                .orElseThrow(EntityNotFoundException::new);

        Disciplina Disciplina = disciplinaRepository.findById(dto.disciplinaId())
                .orElseThrow(EntityNotFoundException::new);

        MatriculaDoAluno MatriculaDoAluno = new MatriculaDoAluno(null, aluno, Disciplina, dto.nota1(), dto.nota2(),
                dto.statusMatricula());

        matriculaRepository.save(MatriculaDoAluno);
    }

    public void trancarMatricula(Long id){
        MatriculaDoAluno matriculaDoAluno = matriculaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (!StatusMatricula.MATRICULADO.equals(matriculaDoAluno.getStatus())) {
            throw new TratamentoDeErros.RequisicaoInvalidaException("BAD_REQUEST, Só é possível trancar a matricula com " +
                    "status de Matriculado");
        }

        matriculaDoAluno.setStatus(StatusMatricula.TRANCADO);
        matriculaRepository.save(matriculaDoAluno);
    }

    public void atualizarNotas(Long MatriculaAlunoId,
                               AtualizarNotasRequest AtualizarNotasRequest){
        MatriculaDoAluno matriculaDoAluno = matriculaRepository.findById(MatriculaAlunoId)
                .orElseThrow(EntityNotFoundException::new);

        if (AtualizarNotasRequest.getNota1() != null){
            matriculaDoAluno.setNota1(AtualizarNotasRequest.getNota1());
        }

        if (AtualizarNotasRequest.getNota2() != null){
            matriculaDoAluno.setNota2(AtualizarNotasRequest.getNota2());
        }

        CalcularMedia(matriculaDoAluno);
        matriculaRepository.save(matriculaDoAluno);
    }

    private void CalcularMedia(MatriculaDoAluno matriculaDoAluno) {
        Double nota1 = matriculaDoAluno.getNota1();
        Double nota2 = matriculaDoAluno.getNota2();

        if (nota1 != null && nota2 != null) {
            Double media = (nota1 + nota2) / 2;
            matriculaDoAluno.setStatus(media > MEDIA_PARA_APROVAÇÃO ? StatusMatricula.APROVADO
                    : StatusMatricula.REPROVADO);
        }
    }

    public HistoricoAlunoResponse emitirHistorico(Long id){
        List<MatriculaDoAluno> matriculasDoAlunos = matriculaRepository.findByAlunoId(id);

        if (matriculasDoAlunos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse aluno não possui matricula");
        }

        HistoricoAlunoResponse historicoAlunoResponse = new HistoricoAlunoResponse();
        historicoAlunoResponse.setNomeAluno(matriculasDoAlunos.get(0).getAluno().getNome());
        historicoAlunoResponse.setCpfAluno(matriculasDoAlunos.get(0).getAluno().getCpf());
        historicoAlunoResponse.setEmailAluno(matriculasDoAlunos.get(0).getAluno().getCpf());

        List<DisciplinaAlunoResponse> disciplinasList = new ArrayList<>();

        for (MatriculaDoAluno matriculaDoAluno : matriculasDoAlunos) {
            DisciplinaAlunoResponse disciplinaAlunoResponse = new DisciplinaAlunoResponse();
            disciplinaAlunoResponse.setNomeDisciplina(matriculaDoAluno.getDisciplina().getNome());
            disciplinaAlunoResponse.setNomeProfessor(matriculaDoAluno.getDisciplina().getProfessor().getNome());
            disciplinaAlunoResponse.setNota1(matriculaDoAluno.getNota1());
            disciplinaAlunoResponse.setNota2(matriculaDoAluno.getNota2());

            if (matriculaDoAluno.getNota1() != null && matriculaDoAluno.getNota2() != null) {
                disciplinaAlunoResponse.setMedia((matriculaDoAluno.getNota1() + matriculaDoAluno.getNota2()) / 2.0);
            } else {
                disciplinaAlunoResponse.setMedia(null);
            }

            disciplinaAlunoResponse.setStatus(matriculaDoAluno.getStatus());

            disciplinasList.add(disciplinaAlunoResponse);
        }
        historicoAlunoResponse.setDisciplinasAlunoResponses(disciplinasList);

        return historicoAlunoResponse;
    }

}
