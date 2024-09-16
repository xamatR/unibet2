package com.labprogweb.unibet2.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labprogweb.unibet2.service.UsuarioService;
import com.labprogweb.unibet2.security.JwtService;
import com.labprogweb.unibet2.Model.entity.Usuario;
import com.labprogweb.unibet2.api.dto.TokenDTO;
import com.labprogweb.unibet2.api.dto.CredenciaisDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/v2/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody Usuario usuario){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioService.salvar(usuario);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try {
            Usuario usuario = Usuario.builder()
                .login(credenciais.getLogin())
                .senha(credenciais.getSenha()).build();
                UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
                String token = jwtService.gerarToken(usuario.getLogin()); // Pass login instead of usuario
                return new TokenDTO(usuario.getLogin(), token);                    
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }


    
}
