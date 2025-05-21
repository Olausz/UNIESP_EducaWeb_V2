package com.example.API.domain.Matricula;

import com.example.API.domain.Disciplina.DisciplinaAlunoResponse;
import lombok.Data;

import java.util.List;

@Data
public class HistoricoAlunoResponse {
    private String nomeAluno;
    private String emailAluno;
    private String cpfAluno;
    private List<DisciplinaAlunoResponse> disciplinasAlunoResponses;
}
