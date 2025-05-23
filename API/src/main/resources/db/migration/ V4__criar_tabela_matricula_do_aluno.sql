CREATE TABLE matricula_do_aluno (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    aluno_id BIGINT NOT NULL,
    disciplina_id BIGINT NOT NULL,
    nota1 DOUBLE,
    nota2 DOUBLE,
    status VARCHAR(50) NOT NULL,

    CONSTRAINT fk_matricula_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    CONSTRAINT fk_matricula_disciplina FOREIGN KEY (disciplina_id) REFERENCES disciplina(id)
);
