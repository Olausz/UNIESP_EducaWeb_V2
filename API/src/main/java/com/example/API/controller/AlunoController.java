package com.example.API.controller;

import com.example.API.domain.Aluno.AlunoDTO;
import com.example.API.domain.Aluno.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAluno(@RequestBody @Valid AlunoDTO alunoDTO) {
        alunoService.criarAluno(alunoDTO);
    }
}
