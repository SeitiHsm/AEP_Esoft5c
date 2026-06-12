package com.AEP2.demo.controllers;

import com.AEP2.demo.DTO.AtualizarStatusDTO;
import com.AEP2.demo.DTO.SolicitacaoDTO;
import com.AEP2.demo.models.Solicitacao;
import com.AEP2.demo.services.ServicoSolicitacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes")
public class ControleSolicitacao {

    @Autowired
    private ServicoSolicitacao servicoSolicitacao;

    // Cidadao: criar solicitacao
    @PostMapping
    public ResponseEntity<Solicitacao> solicitar(@RequestBody SolicitacaoDTO dto) {
        Solicitacao criada = servicoSolicitacao.criar(dto);
        return ResponseEntity.status(201).body(criada);
    }

    // Cidadao e Prestador: listar solicitacoes
    @GetMapping
    public ResponseEntity<List<Solicitacao>> listar() {
        return ResponseEntity.ok(servicoSolicitacao.listar());
    }

    // Cidadao e Prestador: ver historico da solicitacao
    @GetMapping("/{id}")
    public ResponseEntity<Solicitacao> buscar(@PathVariable Integer id) {
        Solicitacao solicitacao = servicoSolicitacao.buscarPorId(id);
        if (solicitacao == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(solicitacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        servicoSolicitacao.excluir(id);
        return ResponseEntity.noContent().build();
    }

    // Prestador: criar historico (atualizar status)
    @PutMapping("/{id}/status")
    public ResponseEntity<Solicitacao> atualizarStatus(@PathVariable Integer id, @RequestBody AtualizarStatusDTO dto) {
        Solicitacao atualizada = servicoSolicitacao.atualizar(id, dto);
        if (atualizada == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(atualizada);
    }
}
