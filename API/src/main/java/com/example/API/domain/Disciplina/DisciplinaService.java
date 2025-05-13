package com.example.API.domain.Disciplina;

import com.example.API.domain.Professor.ProfessorService;
import com.example.API.model.Disciplina;
import com.example.API.model.Professor;
import com.example.API.repository.DisciplinaRepository;
import com.example.API.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    ProfessorRepository professorRepository;

    public void criarDisciplina(DisciplinaDTO dto) {
        Professor professor = professorRepository.findById(dto.professorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado"));

        Disciplina disciplina = new Disciplina(null, dto.nome(), professor);

        disciplinaRepository.save(disciplina);
    }
}
