package central_controle_fogo.com.backend_central_controle_fogo.service;

import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IRepositoryUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private IRepositoryUser repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User getById(Long id) {
        try {
            return repository.findById(id).orElse(null);
        }
        catch (Exception ex) {
            return null;
        }

    }

    public ResponseDTO deactivateUser(Long id) {
        try{
            var user = repository.findById(id).orElse(null);
            if (user == null) {
                return ResponseDTO.erro("usuário não encontrado");
            }
            user.setActive(false);
            var salvo = repository.save(user);
            return salvo != null ? ResponseDTO.sucesso("usuário atualizado com sucesso!")  : ResponseDTO.erro("Erro ao desativar usuário!");
        }
        catch (Exception e){
            return ResponseDTO.erro("Erro ao desativar usuário!");
        }
    }
}
