package com.sgp.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sgp.api.model.Projeto;
import com.sgp.api.service.ProjetoService;

@RestController
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping("/projetos")
    public ResponseEntity<List<Projeto>> listarProjetos() {
        return ResponseEntity.ok().body(projetoService.carregarProjetos());
    }

    @GetMapping("/projetos/{id}")
    public ResponseEntity<Optional<Projeto>> buscarProjeto(@PathVariable("id") Long id) {
        Optional<Projeto> projeto = projetoService.carregarDadosProjetoPeloId(id);

        if (projeto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(projeto);
    }

    @DeleteMapping("/projetos/{id}")
    public ResponseEntity<Void> excluirProjeto(@PathVariable("id") Long id) {
        Optional<Projeto> projeto = projetoService.carregarDadosProjetoPeloId(id);

        if (projeto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        projetoService.deletarProjeto(id);

        return ResponseEntity.noContent().build();
    }

}
