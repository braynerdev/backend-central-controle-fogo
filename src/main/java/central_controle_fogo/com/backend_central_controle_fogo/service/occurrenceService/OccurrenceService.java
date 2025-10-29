package central_controle_fogo.com.backend_central_controle_fogo.service.occurrenceService;

import central_controle_fogo.com.backend_central_controle_fogo.Enum.OccurrenceStatus;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceOnSiteDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrencePaginatorDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceUpdateDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OcurrenceRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.Occurrence;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurenceUsers;

import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IRepositoryUser;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceUsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OccurrenceService {

    @Autowired
    private OccurrenceRepository occurrenceRepository;

    @Autowired
    private OccurrenceUsersRepository occurrenceUsersRepository;

    @Autowired
    private IRepositoryUser userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public OccurrenceResponseDTO getById(Long id) {
        try {
            Occurrence occurrence = occurrenceRepository.findById(id).orElse(null);
            if (occurrence == null) {
                return null;
            }

            OccurrenceResponseDTO dto = modelMapper.map(occurrence, OccurrenceResponseDTO.class);
            dto.setCreateDate(occurrence.getCreatedAt());
            
            if (occurrence.getAddress() != null) {
                dto.setZipCode(occurrence.getAddress().getZipCode());
                dto.setStreet(occurrence.getAddress().getStreet());
                dto.setNumber(String.valueOf(occurrence.getAddress().getNumber()));
                dto.setNeighborhood(occurrence.getAddress().getNeighborhood());
                dto.setCity(occurrence.getAddress().getCity());
                dto.setState(occurrence.getAddress().getState());
                dto.setComplement(occurrence.getAddress().getComplement());
            }

            return dto;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public boolean createOccurrence(OcurrenceRequestDTO dto) {
        try{
            Address address = modelMapper.map(dto.getAddress(), Address.class);
            
            Occurrence occurrence = new Occurrence(
                dto.isOccurrenceHasVictims(),
                dto.getOccurrenceRequester(),
                dto.getOccurrenceRequesterPhoneNumber(),
                dto.getOccurrenceSubType(),
                address
            );

            occurrenceRepository.save(occurrence);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean completeOccurrence(Long occurrenceId, OccurrenceOnSiteDTO dto) {
        try {
            Occurrence occurrence = occurrenceRepository.findById(occurrenceId)
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));

            List<User> users = userRepository.findAllById(dto.getUserIds());
            
            if (users.size() != dto.getUserIds().size()) {
                throw new RuntimeException("Um ou mais usuários não foram encontrados");
            }

            occurrence.setOccurrenceDetails(dto.getOccurrenceDetails());
            occurrence.setLatitude(dto.getLatitude());
            occurrence.setLongitude(dto.getLongitude());
            occurrence.setOccurrenceArrivalTime(dto.getOccurrenceArrivalTime());
            occurrence.setStatus(OccurrenceStatus.CONCLUIDA);

            occurrenceRepository.save(occurrence);

            List<OccurenceUsers> occurrenceUsers = users.stream()
                .map(user -> new OccurenceUsers(occurrence, user))
                .collect(Collectors.toList());

            occurrenceUsersRepository.saveAll(occurrenceUsers);
            return true;
        }
        catch (Exception e) {
            throw new RuntimeException("Erro ao completar ocorrência: " + e.getMessage(), e);
        }
    }

    @Transactional
    public PaginatorGeneric<OccurrencePaginatorDTO> GetPaginatorOccurrence(Pageable pageable, boolean active, String filterGeneric) {
        Page<Occurrence> paginator = occurrenceRepository.findAll(pageable);

        List<OccurrencePaginatorDTO> filterList = paginator.stream()
                .map(o -> modelMapper.map(o, OccurrencePaginatorDTO.class))
                .filter(o -> {
                    boolean matches = true;
                    if (filterGeneric != null && !filterGeneric.isEmpty()) {
                        String requester = o.getOccurrenceRequester() != null ? o.getOccurrenceRequester().toUpperCase() : "";
                        String subType = o.getOccurrenceSubType() != null ? o.getOccurrenceSubType().toUpperCase() : "";
                        matches = requester.contains(filterGeneric.toUpperCase()) || subType.contains(filterGeneric.toUpperCase());
                    }
                    matches &= o.isActive() == active;
                    return matches;
                })
                .collect(Collectors.toList());

        return new PaginatorGeneric<>(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                filterList.size(),
                filterList
        );
    }

    @Transactional
    public ResponseDTO deactivateOccurrence(Long id) {
        try {
            var occurrence = occurrenceRepository.findById(id).orElse(null);
            if (occurrence == null) {
                return ResponseDTO.erro("Ocorrência não encontrada");
            }
            occurrence.setActive(false);
            var saved = occurrenceRepository.save(occurrence);
            return saved != null ? ResponseDTO.sucesso("Ocorrência desativada com sucesso!") : ResponseDTO.erro("Erro ao desativar ocorrência!");
        }
        catch (Exception e) {
            return ResponseDTO.erro("Erro ao desativar ocorrência!");
        }
    }

    @Transactional
    public ResponseDTO updateOccurrence(Long id, OccurrenceUpdateDTO dto) {
        try {
            Occurrence occurrence = occurrenceRepository.findById(id).orElse(null);
            if (occurrence == null) {
                return ResponseDTO.erro("Ocorrência não encontrada!");
            }

            // Atualizar campos básicos da ocorrência
            occurrence.setOccurrenceHasVictims(dto.isOccurrenceHasVictims());
            occurrence.setOccurrenceRequester(dto.getOccurrenceRequester());
            occurrence.setOccurrenceRequesterPhoneNumber(dto.getOccurrenceRequesterPhoneNumber());
            occurrence.setOccurrenceSubType(dto.getOccurrenceSubType());
            occurrence.setOccurrenceDetails(dto.getOccurrenceDetails());
            occurrence.setLatitude(dto.getLatitude());
            occurrence.setLongitude(dto.getLongitude());
            occurrence.setOccurrenceArrivalTime(dto.getOccurrenceArrivalTime());

            // Atualizar endereço
            if (dto.getAddress() != null) {
                Address address = occurrence.getAddress();
                if (address == null) {
                    address = new Address();
                    occurrence.setAddress(address);
                }
                address.setStreet(dto.getAddress().getStreet());
                address.setNumber(dto.getAddress().getNumber());
                address.setComplement(dto.getAddress().getComplement());
                address.setNeighborhood(dto.getAddress().getNeighborhood());
                address.setCity(dto.getAddress().getCity());
                address.setState(dto.getAddress().getState());
                address.setZipCode(dto.getAddress().getZipCode());
            }

            // Atualizar usuários se fornecidos
            if (dto.getUserIds() != null && !dto.getUserIds().isEmpty()) {
                List<User> users = userRepository.findAllById(dto.getUserIds());
                if (users.size() != dto.getUserIds().size()) {
                    return ResponseDTO.erro("Um ou mais usuários não foram encontrados!");
                }

                // Remover usuários antigos
                if (occurrence.getUsers() != null) {
                    occurrenceUsersRepository.deleteAll(occurrence.getUsers());
                }

                // Adicionar novos usuários
                List<OccurenceUsers> occurrenceUsers = users.stream()
                        .map(user -> new OccurenceUsers(occurrence, user))
                        .collect(Collectors.toList());
                occurrenceUsersRepository.saveAll(occurrenceUsers);
            }

            occurrenceRepository.save(occurrence);
            return ResponseDTO.sucesso("Ocorrência atualizada com sucesso!");
        }
        catch (Exception e) {
            return ResponseDTO.erro("Erro ao atualizar ocorrência: " + e.getMessage());
        }
    }
    @Transactional
    public ResponseDTO activateOccurrence(Long id) {
        try {
            var occurrence = occurrenceRepository.findById(id).orElse(null);
            if (occurrence == null) {
                return ResponseDTO.erro("Ocorrência não encontrada!");
            }
            if (occurrence.isActive()) {
                return ResponseDTO.erro("Ocorrência já está ativa!");
            }
            occurrence.setActive(true);
            occurrenceRepository.save(occurrence);
            return ResponseDTO.sucesso("Ocorrência ativada com sucesso!");
        }
        catch (Exception e) {
            return ResponseDTO.erro("Erro ao ativar ocorrência");
        }
    }
}