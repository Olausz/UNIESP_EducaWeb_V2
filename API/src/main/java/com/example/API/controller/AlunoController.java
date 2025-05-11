package com.example.API.controller;

import com.example.API.domain.Aluno.AlunoAtualizadoDTO;
import com.example.API.domain.Aluno.AlunoDTO;
import com.example.API.domain.Aluno.AlunoService;
import com.example.API.model.Aluno;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    public Aluno buscarAlunoPorId(@PathVariable Long id){
        return alunoService.buscarAlunoPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarAluno(@PathVariable Long id, @RequestBody @Valid AlunoAtualizadoDTO dto) {
        alunoService.atualizarAlunoPorId(id, dto);
    }

}
