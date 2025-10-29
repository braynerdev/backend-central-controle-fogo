package central_controle_fogo.com.backend_central_controle_fogo.service.occurrenceService;




import central_controle_fogo.com.backend_central_controle_fogo.Enum.OccurrenceStatus;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceDispatchDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceOnSiteDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.Occurrence;

import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceSubType;

import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceSubTypeRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OccurrenceService {

    @Autowired
    private OccurrenceRepository occurrenceRepository;

    @Autowired
    private OccurrenceSubTypeRepository subTypeRepository;

    @Autowired
    private OccurrenceMapper occurrenceMapper;

    @Transactional(readOnly = true)
    public List<OccurrenceResponseDTO> findAll() {
        return occurrenceRepository.findAll()
                .stream()
                .map(occurrenceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OccurrenceResponseDTO findById(Long id) {
        Occurrence occurrence = occurrenceRepository.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("Ocorrência não encontrada com ID: " + id));
        return occurrenceMapper.toResponseDTO(occurrence);
    }

    // MÉTODO 1: ATENDENTE CRIA (Etapa 1)
    @Transactional
    public OccurrenceResponseDTO createOccurrence(OccurrenceDispatchDTO dto) {
        OccurrenceSubType subType = subTypeRepository.findById(dto.getOccurrenceSubTypeId())
                .orElseThrow(() -> new OpenApiResourceNotFoundException("SubTipo de ocorrência não encontrado com ID: " + dto.getOccurrenceSubTypeId()));

        Occurrence occurrence = new Occurrence();

        // Mapeia os dados da Etapa 1
        mapDispatchDtoToEntity(dto, occurrence, subType);

        // Define o STATUS INICIAL
        occurrence.setStatus(OccurrenceStatus.AGUARDANDO_ATENDIMENTO);

        Occurrence savedOccurrence = occurrenceRepository.save(occurrence);
        return occurrenceMapper.toResponseDTO(savedOccurrence);
    }

    // Etapa 2
    @Transactional
    public OccurrenceResponseDTO addOnSiteReport(Long id, OccurrenceOnSiteDTO dto) {
        Occurrence occurrence = occurrenceRepository.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("Ocorrência não encontrada com ID: " + id));

        // Regra de Negócio: Verifica se já não está finalizada
        if (occurrence.getStatus() == OccurrenceStatus.CONCLUIDA ||
                occurrence.getStatus() == OccurrenceStatus.CANCELADA ||
                occurrence.getStatus() == OccurrenceStatus.FALSO_ALARME) {
            throw new IllegalStateException("Ocorrência já finalizada (status: " + occurrence.getStatus() + ")");
        }

        // Valida o status final
        if (dto.getFinalStatus() != OccurrenceStatus.CONCLUIDA &&
                dto.getFinalStatus() != OccurrenceStatus.FALSO_ALARME) {
            throw new IllegalArgumentException("O status final deve ser CONCLUIDA ou FALSO_ALARME.");
        }

        // Mapeia os dados da Etapa 2
        occurrence.setOccurrenceDetails(dto.getOccurrenceDetails());
        occurrence.setLatitude(dto.getLatitude());
        occurrence.setLongitude(dto.getLongitude());
        occurrence.setOccurrenceArrivalTime(dto.getOccurrenceArrivalTime());
//        occurrence.setInvolvedPeople(dto.getInvolvedPeople());
//        occurrence.setInvolvedVehicles(dto.getInvolvedVehicles());

        occurrence.setStatus(dto.getFinalStatus()); // Define o status final

        Occurrence updatedOccurrence = occurrenceRepository.save(occurrence);
        return occurrenceMapper.toResponseDTO(updatedOccurrence);
    }

    @Transactional
    public boolean deactivate(Long id) {
        try{
            var occurence = occurrenceRepository.findById(id).orElse(null);
            if (occurence == null) {
                return false;
            }
            occurence.setActive(false);
            occurrenceRepository.save(occurence);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }

    @Transactional
    public boolean activate(Long id) {
        try{
            var occurence = occurrenceRepository.findById(id).orElse(null);
            if (occurence == null) {
                return false;
            }
            if (occurence.isActive()) {
                return false;
            }
            occurence.setActive(true);
            occurrenceRepository.save(occurence);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }

    // Mapeia os dados da Etapa 1
    private void mapDispatchDtoToEntity(OccurrenceDispatchDTO dto, Occurrence occurrence, OccurrenceSubType subType) {
        occurrence.setOccurrenceHasVictims(dto.getOccurrenceHasVictims());
        occurrence.setOccurrenceIsPriority(dto.getOccurrenceIsPriority());
        occurrence.setOccurrenceRequester(dto.getOccurrenceRequester());
        occurrence.setOccurrenceRequesterPhoneNumber(dto.getOccurrenceRequesterPhoneNumber());
        occurrence.setAddress(dto.getAddress());
        occurrence.setOccurrenceSubType(subType);
    }


}