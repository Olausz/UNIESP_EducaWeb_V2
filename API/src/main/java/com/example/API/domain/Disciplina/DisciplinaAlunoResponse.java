package com.example.API.domain.Disciplina;

import com.example.API.domain.Enums.StatusMatricula;
import lombok.Data;

@Data
public class DisciplinaAlunoResponse {

    private String nomeDisciplina;
    private String nomeProfessor;
    private Double nota1;
    private Double nota2;
    private Double media;
    private StatusMatricula status;
}
