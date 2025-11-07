package central_controle_fogo.com.backend_central_controle_fogo.service.occurrenceService;

import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.Occurrence;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IRepositoryUser;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceSubTypeRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceUsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OccurrenceService implements IOccurrenceService {

    @Autowired
    private OccurrenceRepository occurrenceRepository;

    @Autowired
    private OccurrenceUsersRepository occurrenceUsersRepository;

    @Autowired
    private OccurrenceSubTypeRepository occurrenceSubTypeRepository;

    @Autowired
    private IRepositoryUser userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public OccurrenceResponseDTO registerOccurence(OccurrenceRequestDTO occurrenceRequestDTO) {
        var address = modelMapper.map(occurrenceRequestDTO.getAddress(), Address.class);
        
        var occurrenceSubType = occurrenceSubTypeRepository.findById(occurrenceRequestDTO.getOccurrenceSubType())
                .orElseThrow(() -> new RuntimeException("Subtipo de ocorrência não encontrado"));
        
        var occurrence = modelMapper.map(occurrenceRequestDTO, Occurrence.class);

        occurrence.setAddress(address);
        occurrence.setOccurrenceSubType(occurrenceSubType);
        occurrenceRepository.save(occurrence);
        
        var responseOccurrence = modelMapper.map(occurrence, OccurrenceResponseDTO.class);
        return responseOccurrence;
    }
}