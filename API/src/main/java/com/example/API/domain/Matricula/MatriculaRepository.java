package com.example.API.domain.Matricula;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaDoAluno, Long > {
        List<MatriculaDoAluno> findByAlunoId(Long alunoId);
}
