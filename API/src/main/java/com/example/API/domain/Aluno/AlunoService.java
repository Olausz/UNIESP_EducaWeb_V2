package com.example.API.domain.Aluno;

import com.example.API.model.Aluno;
import com.example.API.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
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
                .orElseThrow(EntityNotFoundException::new);
    }
    
    public void atualizarAlunoPorId(Long id, AlunoAtualizadoDTO dto) {
        Aluno alunoDoBanco = buscarAlunoPorId(id);

        alunoDoBanco.setNome(dto.nome());
        alunoDoBanco.setEmail(dto.email());
        alunoDoBanco.setCpf(dto.cpf());

        alunoRepository.save(alunoDoBanco);
    }


}
