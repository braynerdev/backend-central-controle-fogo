package central_controle_fogo.com.backend_central_controle_fogo.Enum;

public enum PatentEnum {
    CORONEL("Coronel"),
    TENENTE_CORONEL("Tenente-Coronel"),
    MAJOR("Major"),
    CAPITAO("Capitão"),
    PRIMEIRO_TENENTE("1º Tenente"),
    SEGUNDO_TENENTE("2º Tenente"),
    ASPIRANTE_OFICIAL("Aspirante a Oficial"),
    SUBTENENTE("Subtenente"),
    PRIMEIRO_SARGENTO("1º Sargento"),
    SEGUNDO_SARGENTO("2º Sargento"),
    TERCEIRO_SARGENTO("3º Sargento"),
    CABO("Cabo"),
    SOLDADO("Soldado");

    private final String descricao;

    PatentEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}