package com.example.API.controller;

import com.example.API.domain.Matricula.AtualizarNotasRequest;
import com.example.API.domain.Matricula.HistoricoAlunoResponse;
import com.example.API.domain.Matricula.MatriculaDTO;
import com.example.API.domain.Matricula.MatriculaDoAlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    MatriculaDoAlunoService matriculaDoAlunoService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarMatricula(@RequestBody @Valid MatriculaDTO dto) {
        matriculaDoAlunoService.criarMatricula(dto);
    }

    @PatchMapping("/trancar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void trancarMatricula(@PathVariable Long id){
        matriculaDoAlunoService.trancarMatricula(id);
    }

    @PatchMapping("/atualiza-notas{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarNotas(@RequestBody AtualizarNotasRequest atualizarNotasRequest, @PathVariable Long id){
        matriculaDoAlunoService.atualizarNotas(id, atualizarNotasRequest);
    }

    @GetMapping("/emitir-historico/{alunoId}")
    @ResponseStatus(HttpStatus.OK)
    public HistoricoAlunoResponse emitirHistorico(@PathVariable Long id) {
        return matriculaDoAlunoService.

    }
}
