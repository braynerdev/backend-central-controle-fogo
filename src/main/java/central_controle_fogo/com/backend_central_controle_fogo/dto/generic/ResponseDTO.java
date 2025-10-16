package central_controle_fogo.com.backend_central_controle_fogo.dto.generic;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDTO {

    private boolean sucesso;
    private String mensagem;


    public static ResponseDTO sucesso(String mensagem) {
        return new ResponseDTO(true, mensagem);
    }

    public static ResponseDTO erro(String mensagem) {
        return new ResponseDTO(false, mensagem);
    }
}

