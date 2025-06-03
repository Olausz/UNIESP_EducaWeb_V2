package com.example.API.controller;

import com.example.API.domain.Usuario.DadosCadastroUsuario;
import com.example.API.domain.Usuario.Usuario;
import com.example.API.domain.Usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cadastros")
public class UsuarioController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        if (usuarioRepository.findByLogin(dados.login()) != null) {
            return ResponseEntity.badRequest().body("Login já cadastrado.");
        }

        var senhaCriptografada = passwordEncoder.encode(dados.senha());
        var usuario = new Usuario(dados.login(), senhaCriptografada);

        usuarioRepository.save(usuario);

        var uri = uriBuilder.path("/cadastros/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body("Usuário cadastrado com sucesso!");
    }
}
