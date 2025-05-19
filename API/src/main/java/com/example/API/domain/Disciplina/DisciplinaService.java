package com.example.API.domain.Disciplina;

import com.example.API.domain.Professor.ProfessorDTO;
import com.example.API.model.Disciplina;
import com.example.API.model.Professor;
import com.example.API.repository.DisciplinaRepository;
import com.example.API.repository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    ProfessorRepository professorRepository;

    public void criarDisciplina(DisciplinaDTO dto) {
        Professor professor = professorRepository.findById(dto.professorId())
                .orElseThrow(EntityNotFoundException::new);

        Disciplina disciplina = new Disciplina(null, dto.nome(), professor);
        disciplinaRepository.save(disciplina);
    }

    public Disciplina buscarDisciplinaPorId(Long id) {
        return disciplinaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void deletarDisciplinaPorid(Long id) {
        disciplinaRepository.deleteById(id);
    }

    public void atualizarDisciplinaPorId(Long id, DisciplinaAtualizadoDTO dto){
        Disciplina disciplinaDoBanco = buscarDisciplinaPorId(id);

        disciplinaDoBanco.setId(dto.id());
        disciplinaDoBanco.setNome(dto.nome());

        disciplinaRepository.save(disciplinaDoBanco);
    }

    public List<Disciplina> listarTodasAsDiscplinas() {
        return disciplinaRepository.findAll();
    }



}

