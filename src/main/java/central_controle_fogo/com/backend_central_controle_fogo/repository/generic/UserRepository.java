package central_controle_fogo.com.backend_central_controle_fogo.repository.generic;

import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IUserRepository;

public class UserRepository implements IUserRepository {


    public ResponseDTO createUser(User user){
        var create = repositoryJPA.save(user);
        if (create == null){
            return ResponseDTO.erro("falha ao criar usuario", null);
        }
        return ResponseDTO.sucesso("Usuario criado com sucesso", null);
    }

    public
}
