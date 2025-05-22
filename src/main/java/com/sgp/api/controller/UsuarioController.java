package com.sgp.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgp.api.model.Usuario;
import com.sgp.api.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok().body(usuarioService.carregarUsuariosCadastrados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> buscarUsuarioPeloId(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = usuarioService.obterDadosUsuarioPeloId(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = usuarioService.obterDadosUsuarioPeloId(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        usuarioService.excluirUsuario(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(usuarioService.salvarUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable("id") Long id, @RequestBody Usuario dadosUsuario) {
        Optional<Usuario> usuario = usuarioService.obterDadosUsuarioPeloId(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        dadosUsuario.setId(id);

        return ResponseEntity.ok().body(usuarioService.salvarUsuario(dadosUsuario));
    }
    
    @Autowired
    private UsuarioService usuarioService;

}
