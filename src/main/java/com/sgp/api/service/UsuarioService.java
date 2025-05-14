package com.sgp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgp.api.model.Usuario;
import com.sgp.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    public List<Usuario> carregarUsuariosCadastrados() {
        // TODO: Logica adicional (ex. calcular e retornar idade)
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obterDadosUsuarioPeloId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Autowired
    private UsuarioRepository usuarioRepository;
    
}
