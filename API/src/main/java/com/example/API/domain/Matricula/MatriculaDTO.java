package com.example.API.domain.Matricula;

import com.example.API.domain.Enums.StatusMatricula;


public record MatriculaDTO(Long alunoId, Long disciplinaId, Double nota1, Double nota2, StatusMatricula statusMatricula) {
}
