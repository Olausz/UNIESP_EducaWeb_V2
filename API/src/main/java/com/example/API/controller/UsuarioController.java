package com.example.API.controller;

import com.example.API.domain.Usuario.DadosCadastroUsuario;
import com.example.API.domain.Usuario.Usuario;
import com.example.API.domain.Usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cadastros")
public class UsuarioController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
        var senhaCriptografada =
                passwordEncoder.encode(dados.senha());
        var usuario =
                new Usuario(dados.login(), senhaCriptografada);

        usuarioRepository.save(usuario);
        return null;

    }
}
