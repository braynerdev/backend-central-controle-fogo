package central_controle_fogo.com.backend_central_controle_fogo.service.battalion;

import central_controle_fogo.com.backend_central_controle_fogo.dto.battalion.BattalionRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.battalion.Battalion;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import central_controle_fogo.com.backend_central_controle_fogo.repository.battalion.IBattalionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BattalionService implements IBattalionService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IBattalionRepository battalionRepository;

    @Override
    public ResponseDTO CreateBattalion(BattalionRequestDTO dto) {
        try{
            System.out.println(dto.getAddress());
            Address address =  modelMapper.map(dto.getAddress(), Address.class);
            System.out.println(address);
            var battalion = new Battalion(dto.getName(), dto.getPhoneNumber());
            battalion.setEndereco(address);
            var register = battalionRepository.save(battalion);
            if(register == null){
                return ResponseDTO.erro("Erro ao cadastrar Battalion!222 2");
            }
            return ResponseDTO.sucesso("Battalion cadastrado com sucesso!");
        }
        catch(Exception e){
            System.out.println(e);
            return ResponseDTO.erro("Erro ao cadastrar Battalion!");
        }

    }
}
