package com.example.API.domain.Aluno;

import com.example.API.model.Aluno;
import com.example.API.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public void criarAluno(AlunoDTO dto) {
        Aluno aluno = new Aluno(null, dto.nome(), dto.email(), dto.cpf());
        alunoRepository.save(aluno);
    }
}
