package central_controle_fogo.com.backend_central_controle_fogo.validation;

import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.CadastreRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.battalion.Battalion;
import central_controle_fogo.com.backend_central_controle_fogo.model.patent.Patent;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IRepositoryUser;
import central_controle_fogo.com.backend_central_controle_fogo.repository.battalion.IBattalionRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.patent.IPatent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegisterUserValidation implements IRegisterUserValidation {

    @Autowired
    private IRepositoryUser userRepository;
    @Autowired
    private IBattalionRepository battalionRepository;
    @Autowired
    private IPatent patentRepository;

    public List<String> registerUserValidation(CadastreRequestDTO dto) {
        List<String> errors = new ArrayList<>();

        var matriculate = userRepository.findByMatriculates(dto.getMatriculates()).orElse(null);
        if(matriculate != null){
            errors.add("Já existe um usuario com essa matricula!");
        }

        var cpf = userRepository.findByCpf(dto.getCpf()).orElse(null);
        if(cpf != null){
            errors.add("Já existe um usuario com esse CPF!");
        }

        var email = userRepository.findByEmail(dto.getEmail()).orElse(null);
        if(email != null){
            errors.add("Já existe um usuario com esse e-mail!");
        }

        var phoneNumber = userRepository.findByPhoneNumber(dto.getPhoneNumber()).orElse(null);
        if(phoneNumber != null){
            errors.add("Já existe um usuario com esse numero de telefone!");
        }

        var username = userRepository.findByUsername(dto.getUsername()).orElse(null);
        if(username != null){
            errors.add("Já existe um usuario com esse username!");
        }


        Battalion battalion = battalionRepository.findById(dto.getBattalion())
                .orElse(null);
        if (battalion == null) {
            errors.add("Batalhão não encontrado");
        }
        System.out.println("pattente" + dto.getPatent());
        Patent patent = patentRepository.findById(dto.getPatent())
                .orElse(null);
        if (patent == null) {
            errors.add("Patente não encontrada");
        }

        System.out.println(errors);
        return errors;
    }
}
