package com.example.API.domain.Matricula;

import com.example.API.domain.Enums.StatusMatricula;
import com.example.API.infra.TratamentoDeExceções.TratamentoDeErros;
import com.example.API.model.Aluno;
import com.example.API.model.Disciplina;
import com.example.API.model.MatriculaDoAluno;
import com.example.API.repository.AlunoRepository;
import com.example.API.repository.DisciplinaRepository;
import com.example.API.repository.MatriculaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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





}
