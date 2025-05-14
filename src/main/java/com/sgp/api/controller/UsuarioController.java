package com.sgp.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sgp.api.model.Usuario;
import com.sgp.api.service.UsuarioService;

@RestController
public class UsuarioController {

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok().body(usuarioService.carregarUsuariosCadastrados());
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Optional<Usuario>> buscarUsuarioPeloId(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = usuarioService.obterDadosUsuarioPeloId(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(usuario);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable("id") Long id) {
        usuarioService.excluirUsuario(id);
        
        return ResponseEntity.noContent().build();
    }
    
    @Autowired
    private UsuarioService usuarioService;

}
