package com.example.API.domain.Professor;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    public void criarProfessor(ProfessorDTO dto){
        Professor professor = new Professor(null, dto.nome(), dto.cpf(), dto.email());
        professorRepository.save(professor);
    }

    public Professor buscarProfessorPorId(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void deletarProfessorPorId(Long id) {
        professorRepository.deleteById(id);
    }

    public void atualizaProfessorPorId(Long id, ProfessorAtualizadoDTO dto){
        Professor professorDoBanco = buscarProfessorPorId(id);

        professorDoBanco.setId(dto.id());
        professorDoBanco.setNome(dto.nome());
        professorDoBanco.setCpf(dto.cpf());
        professorDoBanco.setEmail(dto.email());

        professorRepository.save(professorDoBanco);
    }

    public List<Professor> listarTodosProfessores() {
        return professorRepository.findAll();
    }


}
