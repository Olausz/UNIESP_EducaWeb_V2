package com.example.API.controller;

import com.example.API.domain.Professor.ProfessorAtualizadoDTO;
import com.example.API.domain.Professor.ProfessorDTO;
import com.example.API.domain.Professor.ProfessorService;
import com.example.API.model.Professor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarProfessor(@RequestBody @Valid ProfessorDTO professorDTO) {
        professorService.criarProfessor(professorDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Professor buscarProfessorPorId(@PathVariable Long id) {
        return professorService.buscarProfessorPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProfessorPorId(@PathVariable Long id) {
        professorService.deletarProfessorPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarProfessor(@PathVariable Long id, @RequestBody @Valid ProfessorAtualizadoDTO professorDTO){
        professorService.atualizaProfessorPorId(id, professorDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Professor> listarTodosProfessores() {
        return professorService.listarTodosProfessores();
    }
}