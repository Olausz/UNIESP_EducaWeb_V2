package com.example.API.controller;

import com.example.API.domain.Aluno.AlunoDTO;
import com.example.API.domain.Disciplina.DisciplinaDTO;
import com.example.API.domain.Disciplina.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    DisciplinaService disciplinaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarDisciplina(@RequestBody @Valid DisciplinaDTO disciplinaDTO) {
        disciplinaService.criarDisciplina(disciplinaDTO);
    }
}
