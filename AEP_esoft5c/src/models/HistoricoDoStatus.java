package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoricoDoStatus {
    private EnumStatus status;
    private String comentario;
    private String responsavel;
    private LocalDateTime data;

    public HistoricoDoStatus(EnumStatus status, String comentario, String responsavel) {
        this.status = status;
        this.comentario = comentario;
        this.responsavel = responsavel;
        this.data = LocalDateTime.now();
    }

    public void exibir() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("[" + data.format(formatter) + "] Status: " + status);
        System.out.println("Responsável: " + responsavel + " | Comentário: " + comentario);
        System.out.println("-------------------------------------------------");
    }
}