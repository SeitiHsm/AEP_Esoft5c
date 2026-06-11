package com.AEP2.demo.controllers;

import com.AEP2.demo.DTO.AtualizarStatusDTO;
import com.AEP2.demo.DTO.SolicitacaoDTO;
import com.AEP2.demo.models.Solicitacao;
import com.AEP2.demo.services.ServicoSolicitacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes")
public class ControleSolicitacao {

    @Autowired
    private ServicoSolicitacao servicoSolicitacao;

    // O JSON enviado pelo usuário é convertido automaticamente
    // para um objeto SolicitacaoDTO pelo Spring.
    @PostMapping
    public Solicitacao solicitar(@RequestBody SolicitacaoDTO dto){
        return servicoSolicitacao.criar(dto);
    }
    @GetMapping
    public List<Solicitacao> listar(){
        return servicoSolicitacao.listar();
    }

    @GetMapping("/{id}")
    public Solicitacao buscar(@PathVariable Integer id){
        return servicoSolicitacao.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Integer id){
        servicoSolicitacao.excluir(id);
    }

    @PutMapping("/{id}/status")
    public Solicitacao atualizarStatus(@PathVariable Integer id, @RequestBody AtualizarStatusDTO dto){
        return servicoSolicitacao.atualizar(id, dto);
    }
}
