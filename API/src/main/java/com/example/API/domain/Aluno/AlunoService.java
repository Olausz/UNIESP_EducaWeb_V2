package com.example.API.domain.Aluno;

import com.example.API.model.Aluno;
import com.example.API.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public void criarAluno(AlunoDTO dto) {
        Aluno aluno = new Aluno(null, dto.nome(), dto.email(), dto.cpf());
        alunoRepository.save(aluno);
    }

    public Aluno buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
    }
    
    public void atualizarAlunoPorId(Long id, AlunoAtualizadoDTO dto) {
        Aluno aluno = buscarAlunoPorId(id);

        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setCpf(dto.cpf());

        alunoRepository.save(aluno);
    }


}
