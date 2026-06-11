package com.AEP2.demo.services;

import com.AEP2.demo.DTO.AtualizarStatusDTO;
import com.AEP2.demo.DTO.SolicitacaoDTO;
import com.AEP2.demo.enums.EnumStatus;
import com.AEP2.demo.models.Solicitacao;
import com.AEP2.demo.repositories.SolicitacaoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoSolicitacao {

    @Autowired
    private SolicitacaoRepositorio solicitacaoRepositorio;

    // Recebe os dados do DTO e cria uma entidade Solicitacao.
    // O DTO serve apenas para transporte de dados.
    public Solicitacao criar(SolicitacaoDTO dto) {
        Solicitacao solicitacao = new Solicitacao();

        solicitacao.setCategoria(dto.getCategoria());
        solicitacao.setDescricao(dto.getDescricao());
        solicitacao.setBairro(dto.getBairro());

        if (dto.isAnonimo()) {
            solicitacao.setNome("Anônimo");
        } else {
            solicitacao.setNome(dto.getNome());
        }

        solicitacao.setPrioridade(dto.getPrioridade());
        solicitacao.setStatus(EnumStatus.ABERTO);

        return solicitacaoRepositorio.save(solicitacao);
    }

    public List<Solicitacao> listar() {
        return solicitacaoRepositorio.findAll();
    }

    public Solicitacao buscarPorId(Integer protocolo) {
        return solicitacaoRepositorio.findById(protocolo).orElse(null);
    }

    public void excluir(Integer protocolo) {
        solicitacaoRepositorio.deleteById(protocolo);
    }

    /*
     Atualiza apenas o status da solicitação.

     Exemplo:

     ABERTO -> TRIAGEM
     TRIAGEM -> EM_EXECUCAO

     O restante das informações permanece igual.

     Utilizamos um DTO separado para garantir que
     apenas o status seja alterado.
    */
    public Solicitacao atualizar(Integer protocolo, AtualizarStatusDTO dto) {
        Solicitacao solicitacao = solicitacaoRepositorio.findById(protocolo).orElse(null);

        if (solicitacao == null) {
            return null;
        }

        // Verifica se a transição de status é válida
        // de acordo com as regras do sistema.
        if (!solicitacao.podeMudarPara(dto.getNovoStatus())) {
            return null;
        }

        solicitacao.setStatus((dto.getNovoStatus()));

        return solicitacaoRepositorio.save(solicitacao);
    }

}
