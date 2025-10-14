package central_controle_fogo.com.backend_central_controle_fogo.dto.generic;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseDTO<T> {
    private boolean sucesso;
    private String mensagem;
    private T dados;


    public static <T> ResponseDTO<T> sucesso(String mensagem, T dados) {
        return new ResponseDTO<>(true, mensagem, dados);
    }

    public static <T> ResponseDTO<T> erro(String mensagem, T dados) {
        return new ResponseDTO<>(false, mensagem, dados);
    }
}

