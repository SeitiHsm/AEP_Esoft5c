package com.AEP2.demo.models;

import com.AEP2.demo.enums.EnumStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
/*
===============================================================
             PARA TU DANIEL(dps apaga se entender)
===============================================================
 Cada registro de histórico pertence a apenas uma solicitação.

 Exemplo:

 Histórico:
 "Mudou para TRIAGEM"

 Esse histórico pertence à Solicitação #1.

 Como vários históricos pertencem a uma mesma solicitação,
 utilizamos @ManyToOne.

 Muitos Históricos -> Uma Solicitação
*/
@Entity
@Table(name = "historico_status")
public class HistoricoStatus {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Enumerated(EnumType.STRING)
        private EnumStatus status;

        private String comentario;

        private String responsavel;

        private LocalDateTime data;

        @ManyToOne
        @JoinColumn(name = "solicitacao_id")
        private Solicitacao solicitacao;

        public HistoricoStatus() {
        }

        public HistoricoStatus(
                EnumStatus status,
                String comentario,
                String responsavel,
                Solicitacao solicitacao
        ) {
            this.status = status;
            this.comentario = comentario;
            this.responsavel = responsavel;
            this.solicitacao = solicitacao;
            this.data = LocalDateTime.now();
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
}
