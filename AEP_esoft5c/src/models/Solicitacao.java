package models;

import java.util.ArrayList;
import java.util.List;

public class Solicitacao {
    private int protocolo;
    private EnumStatus status;
    private String categoria;
    private String descricao;
    private String bairro;
    private String nome;
    private EnumPrioridade prioridade;
    private List<HistoricoDoStatus> historico;

    public Solicitacao(int protocolo, String categoria, String descricao, String bairro, String nome, boolean anonimo, EnumPrioridade prioridade) {
        this.protocolo = protocolo;
        this.status = EnumStatus.ABERTO;
        this.categoria = categoria;
        this.descricao = descricao;
        this.bairro = bairro;
        this.prioridade = prioridade;
        this.nome = anonimo ? "ANÔNIMO" : nome;
        this.historico = new ArrayList<>();
        this.historico.add(new HistoricoDoStatus(EnumStatus.ABERTO, "Solicitação criada", "SISTEMA"));
    }

    public boolean podeMudarPara(EnumStatus novoStatus) {
        if (this.status == EnumStatus.ABERTO) return novoStatus == EnumStatus.TRIAGEM;
        if (this.status == EnumStatus.TRIAGEM) return novoStatus == EnumStatus.EM_EXECUCAO;
        if (this.status == EnumStatus.EM_EXECUCAO) return novoStatus == EnumStatus.RESOLVIDO;
        if (this.status == EnumStatus.RESOLVIDO) return novoStatus == EnumStatus.ENCERRADO;
        return false;
    }

    public void atualizarStatus(EnumStatus novoStatus, String comentario, String responsavel) {
        if (podeMudarPara(novoStatus)) {
            this.status = novoStatus;
            historico.add(new HistoricoDoStatus(novoStatus, comentario, responsavel));
        }
    }

    public void exibir() {
        System.out.println("\n========= PROTOCOLO: " + protocolo + " =========");
        System.out.println("Status:     " + status);
        System.out.println("Prioridade: " + prioridade);
        System.out.println("Categoria:  " + categoria);
        System.out.println("Bairro:     " + bairro);
        System.out.println("Solicitante:" + nome);
        System.out.println("Descrição:  " + descricao);
        System.out.println("========================================");
    }

    public void mostrarHistorico() {
        System.out.println("\n>>> HISTÓRICO DE ATENDIMENTO:");
        for (HistoricoDoStatus h : historico) h.exibir();
    }

    public int getProtocolo() { return protocolo; }
    public EnumStatus getStatus() { return status; }
}