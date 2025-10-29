package central_controle_fogo.com.backend_central_controle_fogo.service.patent;

import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.PatentResponseAllDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.UserPaginatorDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.patent.PatentResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.patent.PatentResquestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.model.patent.Patent;
import central_controle_fogo.com.backend_central_controle_fogo.repository.patent.IPatentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatentService implements IPatentService{

    @Autowired
    private IPatentRepository patentRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PatentResponseDTO createdPatent(PatentResquestDTO patentResquestDTO) {
        try{
            Patent patent = modelMapper.map(patentResquestDTO, Patent.class);
            patentRepository.save(patent);
            PatentResponseDTO patentResponseDTO  = modelMapper.map(patent,PatentResponseDTO.class);
            return patentResponseDTO;
        }
        catch(Exception e){
            return null;
        }
    }



    @Override
    public PatentResponseDTO updatePatent(Long id, PatentResquestDTO patentResquestDTO) {
        var repositoryPatent = patentRepository.findById(id).orElse(null);
        if(repositoryPatent == null){
            return null;
        }
        modelMapper.map(patentResquestDTO, repositoryPatent);
        patentRepository.save(repositoryPatent);
        return modelMapper.map(repositoryPatent,PatentResponseDTO.class);

    }

    @Override
    public PatentResponseDTO getByIdPatent(Long id) {
        var repositoryPatent = patentRepository.findById(id).orElse(null);
        if(repositoryPatent == null){
            return null;
        }
        return modelMapper.map(repositoryPatent,PatentResponseDTO.class);
    }

    @Override
    public PatentResponseAllDTO getAllPatents() {
        try{
            var repositoryPatent = patentRepository.findAll();
            
            if(repositoryPatent == null || repositoryPatent.isEmpty()){
                return new PatentResponseAllDTO();
            }
            
            List<PatentResponseDTO> patentList = repositoryPatent.stream()
                    .map(patent -> modelMapper.map(patent, PatentResponseDTO.class))
                    .collect(Collectors.toList());
            
            return new PatentResponseAllDTO(patentList);
        }
        catch(Exception e){
            return new PatentResponseAllDTO();
        }
    }

    @Override
    public PaginatorGeneric<PatentResponseDTO> GetPaginatorPatent(Pageable pageable, boolean active, String filterGeneric) {
        Page<Patent> paginator = patentRepository.findAll(pageable);

        List<PatentResponseDTO> filterList = paginator.stream()
                .map(p -> modelMapper.map(p, PatentResponseDTO.class))
                .filter(p -> {
                    boolean matches = true;
                    if (filterGeneric != null && !filterGeneric.isEmpty()) {
                        matches = p.getName().contains(filterGeneric);
                    }
                    matches &= p.isActive() == active;
                    return matches;
                })
                .collect(Collectors.toList());

        var paginatorGeneric = new PaginatorGeneric<PatentResponseDTO>(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                filterList.size(),
                filterList
        );
        return paginatorGeneric;
    }

    @Override
    public ResponseDTO DeactivatePatent(Long id) {
        var repositoryPatent = patentRepository.findById(id).orElse(null);
        if(repositoryPatent == null){
            return ResponseDTO.erro("impossivel desativar patente!");
        }
        if (!repositoryPatent.isActive()){
            return ResponseDTO.erro("Patente já esta desativada!");
        }
        repositoryPatent.setActive(false);
        patentRepository.save(repositoryPatent);
        return ResponseDTO.sucesso("Patente desatiada com sucesso!");
    }

    @Override
    public ResponseDTO ActivatePatent(Long id) {
        var repositoryPatent = patentRepository.findById(id).orElse(null);
        if(repositoryPatent == null){
            return ResponseDTO.erro("impossivel desativar patente!");
        }
        if (repositoryPatent.isActive()){
            return ResponseDTO.erro("Patente já esta desativada!");
        }
        repositoryPatent.setActive(false);
        patentRepository.save(repositoryPatent);
        return ResponseDTO.sucesso("Patente desatiada com sucesso!");
    }
}
