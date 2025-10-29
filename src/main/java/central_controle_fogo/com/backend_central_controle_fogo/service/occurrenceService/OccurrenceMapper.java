package central_controle_fogo.com.backend_central_controle_fogo.service.occurrenceService;



import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceSubTypeResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceTypeResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.Occurrence;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceSubType;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceType;
import org.springframework.stereotype.Component;

@Component
public class OccurrenceMapper {

    // Converte Entidade Occurrence -> OccurrenceResponseDTO
    public OccurrenceResponseDTO toResponseDTO(Occurrence occurrence) {
        OccurrenceResponseDTO dto = new OccurrenceResponseDTO();
        dto.setId(occurrence.getId());
        if (occurrence.getCreatedAt() != null) {
            dto.setCreatedAt(occurrence.getCreatedAt().toLocalDateTime());
        }
        if (occurrence.getUpdatedAt() != null) {
            dto.setUpdatedAt(occurrence.getUpdatedAt().toLocalDateTime());
        }
        // Fase 1
        dto.setOccurrenceHasVictims(occurrence.isOccurrenceHasVictims());
        dto.setOccurrenceIsPriority(occurrence.isOccurrenceIsPriority());
        dto.setOccurrenceRequester(occurrence.getOccurrenceRequester());
        dto.setOccurrenceRequesterPhoneNumber(occurrence.getOccurrenceRequesterPhoneNumber());
        dto.setAddress(occurrence.getAddress());

        // SubTipo
        if (occurrence.getOccurrenceSubType() != null) {
            dto.setOccurrenceSubType(toSubTypeResponseDTO(occurrence.getOccurrenceSubType()));
        }

        // Controle
        dto.setStatus(occurrence.getStatus());

        // Fase 2
        dto.setOccurrenceDetails(occurrence.getOccurrenceDetails());
        dto.setLatitude(occurrence.getLatitude());
        dto.setLongitude(occurrence.getLongitude());
        dto.setOccurrenceArrivalTime(occurrence.getOccurrenceArrivalTime());
//        dto.setInvolvedPeople(occurrence.getInvolvedPeople());
//        dto.setInvolvedVehicles(occurrence.getInvolvedVehicles());


        return dto;
    }

    private OccurrenceSubTypeResponseDTO toSubTypeResponseDTO(OccurrenceSubType subType) {
        OccurrenceSubTypeResponseDTO subDto = new OccurrenceSubTypeResponseDTO();
        subDto.setId(subType.getId());
        subDto.setName(subType.getName());

        if (subType.getOccurrenceType() != null) {
            OccurrenceType type = subType.getOccurrenceType();
            OccurrenceTypeResponseDTO typeDto = new OccurrenceTypeResponseDTO();
            typeDto.setId(type.getId());
            typeDto.setName(type.getName());
            subDto.setOccurrenceType(typeDto);
        }
        return subDto;
    }
}