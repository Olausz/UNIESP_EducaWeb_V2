package com.example.API.domain.Aluno;

import com.example.API.model.Aluno;
import com.example.API.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public void criarAluno(AlunoDTO dto) {
        Aluno aluno = new Aluno(null, dto.nome(), dto.email(), dto.cpf());
        alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void deletarAlunoPorId(Long id) {
        alunoRepository.deleteById(id);
    }
    
    public void atualizarAlunoPorId(Long id, AlunoAtualizadoDTO dto) {
        Aluno alunoDoBanco = buscarAlunoPorId(id);

        alunoDoBanco.setNome(dto.nome());
        alunoDoBanco.setEmail(dto.email());
        alunoDoBanco.setCpf(dto.cpf());

        alunoRepository.save(alunoDoBanco);
    }


}
