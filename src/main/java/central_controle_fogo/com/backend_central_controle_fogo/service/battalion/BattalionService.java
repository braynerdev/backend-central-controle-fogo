package central_controle_fogo.com.backend_central_controle_fogo.service.battalion;

import central_controle_fogo.com.backend_central_controle_fogo.dto.battalion.BattalionRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.battalion.BattalionResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.battalion.BattalionResponsePaginatorDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.battalion.Battalion;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import central_controle_fogo.com.backend_central_controle_fogo.repository.address.IAddressRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.battalion.IBattalionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BattalionService implements IBattalionService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IBattalionRepository battalionRepository;
    @Autowired
    private IAddressRepository addressRepository;

    @Override
    public ResponseDTO CreateBattalion(BattalionRequestDTO dto) {
        try{
            Address address =  modelMapper.map(dto.getAddress(), Address.class);
            var battalion = new Battalion(dto.getName(), dto.getPhoneNumber(), dto.getEmail());
            battalion.setEndereco(address);
            var register = battalionRepository.save(battalion);
            if(register == null){
                return ResponseDTO.erro("Erro ao cadastrar Battalion!");
            }
            return ResponseDTO.sucesso("Battalion cadastrado com sucesso!");
        }
        catch(Exception e){
            return ResponseDTO.erro("Erro ao cadastrar Battalion!");
        }

    }

    @Override
    public BattalionResponseDTO GetBattalionById(Long id) {
        try{
            var battalion = battalionRepository.findById(id);
            System.out.println("batalhão" + battalion);
            return modelMapper.map(battalion, BattalionResponseDTO.class);
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public ResponseDTO UpdateBattalion(Long id, BattalionRequestDTO dto) {
        try{
            var battallion = battalionRepository.findById(id).orElse(null);
            if(battallion == null){
                return ResponseDTO.erro("Id inválido");
            }
            var endereco = addressRepository.findById(battallion.getEndereco().getId()).orElse(null);
            if(endereco == null){
                return  ResponseDTO.erro("Endereço inexistente");
            }

            var modelBattalion = battallion;
            var modelAddress = endereco;
            modelMapper.map(dto, modelBattalion);
            modelMapper.map(dto.getAddress(), modelAddress);

            var updateAddress = addressRepository.save(modelAddress);
            if(updateAddress == null){
                return ResponseDTO.erro("Erro ao atualizar endereco");
            }
            var updatedBattalion = battalionRepository.save(modelBattalion);
            if(updatedBattalion == null){
                return ResponseDTO.erro("Erro ao atualizar batalhão!");
            }

            return ResponseDTO.sucesso("Batalhão atualizado com sucesso!");
        }
        catch(Exception e){
            return ResponseDTO.erro("Erro ao atualizar batalhão!");
        }


    }

    @Override
    public PaginatorGeneric<BattalionResponsePaginatorDTO> GetPaginatorBattalion(Pageable pageable, String name, boolean active) {

        Page<Battalion> paginator = battalionRepository.findAll(pageable);

        List<BattalionResponsePaginatorDTO> filterList = paginator.stream()
                .map(b -> modelMapper.map(b, BattalionResponsePaginatorDTO.class))
                .filter(b -> {
                    boolean matches = true;
                    if (name != null && !name.isEmpty()) {
                        matches = b.getName().toLowerCase().contains(name.toLowerCase());
                    }
                    matches = matches && b.isActive() == active;
                    return matches;
                })
                .collect(Collectors.toList());


        var paginatorGeneric = new PaginatorGeneric<BattalionResponsePaginatorDTO>(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                filterList.size(),
                filterList
        );
        return paginatorGeneric;
    }

    @Override
    public boolean deactivateBattalion(Long id) {
        var battalion = battalionRepository.findById(id).orElse(null);
        if(battalion == null){
            return false;
        }
        battalion.setActive(false);
        battalionRepository.save(battalion);
        return true;
    }

    @Override
    public boolean activateBattalion(Long id) {
        var battalion = battalionRepository.findById(id).orElse(null);
        if(battalion == null || battalion.isActive()){
            return false;
        }
        battalion.setActive(true);
        battalionRepository.save(battalion);
        return true;
    }


}
