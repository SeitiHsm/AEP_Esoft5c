package models;

public enum EnumStatus {
    ABERTO("Aberto"),
    TRIAGEM("Em Triagem"),
    EM_EXECUCAO("Em Execução"),
    RESOLVIDO("Resolvido"),
    ENCERRADO("Encerrado");

    private String descricao;


    EnumStatus(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    public static EnumStatus deInt(int opcao) {
        switch (opcao) {
            case 1: return TRIAGEM;
            case 2: return EM_EXECUCAO;
            case 3: return RESOLVIDO;
            case 4: return ENCERRADO;
            default: return ABERTO;
        }
    }
}