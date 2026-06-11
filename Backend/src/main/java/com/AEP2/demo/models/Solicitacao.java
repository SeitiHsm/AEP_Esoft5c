package com.AEP2.demo.models;

import com.AEP2.demo.enums.EnumPrioridade;
import com.AEP2.demo.enums.EnumStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*
===============================================================
             PARA TU DANIEL(dps apaga se entender)
===============================================================
 Uma solicitação pode possuir vários registros de histórico.

 Exemplo:

 Solicitação #1
   -> ABERTO
   -> TRIAGEM
   -> EM_EXECUCAO
   -> RESOLVIDO
   -> ENCERRADO

 Portanto:
 1 Solicitação -> Muitos Históricos

 Por isso usamos @OneToMany.
*/
@Entity
@Table(name = "solicitacoes")
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer protocolo;

    @Enumerated(EnumType.STRING)
    private EnumStatus status;

    @Enumerated(EnumType.STRING)
    private EnumPrioridade prioridade;

    private String categoria;
    private String descricao;
    private String bairro;
    private String nome;

    @OneToMany(
            mappedBy = "solicitacao",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<HistoricoStatus> historico = new ArrayList<>();

    public Solicitacao(){
    }

    public boolean podeMudarPara(EnumStatus novoStatus) {

        if(status == EnumStatus.ABERTO)
            return novoStatus == EnumStatus.TRIAGEM;

        if(status == EnumStatus.TRIAGEM)
            return novoStatus == EnumStatus.EM_EXECUCAO;

        if(status == EnumStatus.EM_EXECUCAO)
            return novoStatus == EnumStatus.RESOLVIDO;

        if(status == EnumStatus.RESOLVIDO)
            return novoStatus == EnumStatus.ENCERRADO;

        return false;
    }

    public Integer getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Integer protocolo) {
        this.protocolo = protocolo;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public EnumPrioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(EnumPrioridade prioridade) {
        this.prioridade = prioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<HistoricoStatus> getHistorico() {
        return historico;
    }

    public void setHistorico(List<HistoricoStatus> historico) {
        this.historico = historico;
    }
}
