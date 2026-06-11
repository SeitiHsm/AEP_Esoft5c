package models;

public enum EnumPrioridade {
    ALTA(1, "Alta"),
    MEDIA(2, "Média"),
    BAIXA(3, "Baixa");

    private final int nivel;
    private final String descricao;

    EnumPrioridade(int nivel, String descricao) {
        this.nivel = nivel;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
    
    public static EnumPrioridade deInt(int opcao) {
        for (EnumPrioridade p : EnumPrioridade.values()) {
            if (p.nivel == opcao) {
                return p;
            }
        }
        return BAIXA; // Padrão caso digitem algo inválido
    }
}