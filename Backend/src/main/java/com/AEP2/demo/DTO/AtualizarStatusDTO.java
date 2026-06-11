package com.AEP2.demo.DTO;

import com.AEP2.demo.enums.EnumStatus;
/*
===============================================================
             PARA TU DANIEL(dps apaga se entender)
===============================================================
 DTO (Data Transfer Object)

 É um objeto usado para transportar dados entre o cliente
 (Postman, Front-End, App) e a API.

 Em vez de receber uma entidade completa do banco,
 recebemos apenas os dados necessários para criar uma solicitação.

 Exemplo:

 O usuário envia:

 {
   "categoria":"Iluminacao",
   "descricao":"Poste apagado",
   "bairro":"Centro"
 }

 Esses dados chegam primeiro no DTO.

 Vantagens:
 - Evita expor a entidade completa.
 - Facilita validações.
 - Permite controlar exatamente quais dados serão recebidos.
 - Mantém a arquitetura organizada.
*/
public class AtualizarStatusDTO {
    private EnumStatus novoStatus;
    private String comentario;
    private String responsavel;

    public EnumStatus getNovoStatus() {
        return novoStatus;
    }

    public void setNovoStatus(EnumStatus novoStatus) {
        this.novoStatus = novoStatus;
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
}
