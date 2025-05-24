package com.example.API.controller;

import com.example.API.domain.Disciplina.DisciplinaAtualizadoDTO;
import com.example.API.domain.Disciplina.DisciplinaDTO;
import com.example.API.domain.Disciplina.DisciplinaService;
import com.example.API.domain.Disciplina.Disciplina;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
@SecurityRequirement(name = "bearer-key")
public class DisciplinaController {

    @Autowired
    DisciplinaService disciplinaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarDisciplina(@RequestBody @Valid DisciplinaDTO disciplinaDTO) {
        disciplinaService.criarDisciplina(disciplinaDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Disciplina buscarDisciplinaPorId(@PathVariable Long id) {
        return disciplinaService.buscarDisciplinaPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarDisciplinaPorId(@PathVariable Long id) {
        disciplinaService.deletarDisciplinaPorid(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarDisciplina(@PathVariable Long id, @RequestBody @Valid DisciplinaAtualizadoDTO disciplinaDTO) {
        disciplinaService.atualizarDisciplinaPorId(id, disciplinaDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Disciplina> listarTodasAsDisciplinas() {
        return disciplinaService.listarTodasAsDiscplinas();
    }
}
