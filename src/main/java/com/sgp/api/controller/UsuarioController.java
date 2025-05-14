package com.sgp.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sgp.api.model.Usuario;
import com.sgp.api.service.UsuarioService;

@RestController
public class UsuarioController {

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        return usuarioService.carregarUsuariosCadastrados();
    }

    @GetMapping("/usuarios/{id}")
    public Optional<Usuario> buscarUsuarioPeloId(@PathVariable("id") Long id) {
        return usuarioService.obterDadosUsuarioPeloId(id);
    }
    
    @Autowired
    private UsuarioService usuarioService;

}
