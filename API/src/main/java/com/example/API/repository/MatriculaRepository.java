package com.example.API.repository;

import com.example.API.model.MatriculaDoAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaDoAluno, Long > {
}
