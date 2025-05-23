package com.example.API.domain.Matricula;


import com.example.API.domain.Aluno.Aluno;
import com.example.API.domain.Disciplina.Disciplina;
import com.example.API.domain.Enums.StatusMatricula;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class MatriculaDoAluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    private Double nota1;

    private Double nota2;

    @Enumerated(EnumType.STRING)
    private StatusMatricula status;
}
