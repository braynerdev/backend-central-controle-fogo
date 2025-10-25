package central_controle_fogo.com.backend_central_controle_fogo.dto.generic;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ResponseListMessageDTO {

    private boolean sucesso;
    private List<String> mensagens;

    public static ResponseListMessageDTO sucesso(List<String> mensagens) {
        return new ResponseListMessageDTO(true, mensagens);
    }

    public static ResponseListMessageDTO erro(List<String> mensagens) {
        return new ResponseListMessageDTO(false, mensagens);
    }
}