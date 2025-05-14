package com.example.API.domain.Disciplina;

import com.example.API.domain.Professor.ProfessorDTO;
import com.example.API.model.Disciplina;
import com.example.API.model.Professor;
import com.example.API.repository.DisciplinaRepository;
import com.example.API.repository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    ProfessorRepository professorRepository;

    public void criarDisciplina(DisciplinaDTO dto) {
        Professor professor = professorRepository.findById(dto.professorId())
                .orElseThrow(EntityNotFoundException::new); // usa a exceção padrão

        Disciplina disciplina = new Disciplina(null, dto.nome(), professor);
        disciplinaRepository.save(disciplina);
    }
}

