package central_controle_fogo.com.backend_central_controle_fogo.service.occurrenceService;


import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.UserPaginatorDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.UserResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrencePaginatorDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceUpdateDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceFirst.OccurrenceFirstRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceFirst.OccurrenceFirstResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceSecond.OccurrenceSecondRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceSecond.OccurrenceSecondResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.vehicle.VehicleResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceUsers;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.Occurrence;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceVehicles;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IRepositoryUser;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.*;
import central_controle_fogo.com.backend_central_controle_fogo.repository.vehicle.IVehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OccurrenceService implements IOccurrenceService {
    @Autowired
    private OccurrenceRepository occurrenceRepository;
    @Autowired
    private OccurrenceUsersRepository occurrenceUsersRepository;
    @Autowired
    private OccurrenceSubTypeRepository occurrenceSubTypeRepository;
    @Autowired
    private OccurrenceStatusRepository occurrenceStatusRepository;
    @Autowired
    private IRepositoryUser userRepository;
    @Autowired
    private OccurrenceVehiclesRepository occurrenceVehiclesRepository;
    @Autowired
    private IVehicleRepository vehicleRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public OccurrenceFirstResponseDTO registerOccurence(OccurrenceFirstRequestDTO occurrenceFirstRequestDTO) {
        var address = modelMapper.map(occurrenceFirstRequestDTO.getAddress(), Address.class);
        
        var occurrenceSubType = occurrenceSubTypeRepository.findById(occurrenceFirstRequestDTO.getOccurrenceSubType())
                .orElseThrow(() -> new RuntimeException("Subtipo de ocorrência não encontrado"));
        var occurrenceStatus = occurrenceStatusRepository.findById(occurrenceFirstRequestDTO.getStatus())
                .orElseThrow(() -> new RuntimeException("status não encontrado"));
        var occurrence = modelMapper.map(occurrenceFirstRequestDTO, Occurrence.class);

        occurrence.setAddress(address);
        occurrence.setOccurrenceSubType(occurrenceSubType);
        occurrence.setStatus(occurrenceStatus);

        occurrenceRepository.save(occurrence);

        var occurrenceFirstResponseDTO = modelMapper.map(occurrence, OccurrenceFirstResponseDTO.class);

        occurrenceFirstResponseDTO.setOccurrenceNature(occurrenceSubType.getOccurrenceType().getNature().getName());
        occurrenceFirstResponseDTO.setOccurrenceType(occurrenceSubType.getOccurrenceType().getName());
        occurrenceFirstResponseDTO.setOccurrenceSubType(occurrenceSubType.getName());
        occurrenceFirstResponseDTO.setStatus(occurrenceStatus.getName());

        return occurrenceFirstResponseDTO;
    }

    @Override
    public OccurrenceSecondResponseDTO registerComplement(OccurrenceSecondRequestDTO occurrenceSecondRequestDTO) {

        var occurrence = occurrenceRepository.findById(occurrenceSecondRequestDTO.getOccurrenceId())
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));
        var occurrenceStatus = occurrenceStatusRepository.findById(occurrenceSecondRequestDTO.getStatus())
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));

        occurrence.setOccurrenceDetails(occurrenceSecondRequestDTO.getOccurrenceDetails());
        occurrence.setLatitude(occurrenceSecondRequestDTO.getLatitude());
        occurrence.setLongitude(occurrenceSecondRequestDTO.getLongitude());
        occurrence.setOccurrenceArrivalTime(occurrenceSecondRequestDTO.getOccurrenceArrivalTime());
        occurrence.setStatus(occurrenceStatus);

        occurrenceRepository.save(occurrence);

        
        var existingUsers = occurrenceUsersRepository.findByOccurrence(occurrence);
        occurrenceUsersRepository.deleteAll(existingUsers);
        
        var existingVehicles = occurrenceVehiclesRepository.findByOccurrence(occurrence);
        occurrenceVehiclesRepository.deleteAll(existingVehicles);

        
        var users = userRepository.findAllById(occurrenceSecondRequestDTO.getUserIds());
        var vehicles = vehicleRepository.findAllById(occurrenceSecondRequestDTO.getVehicles());

        List<OccurrenceVehicles> occurrenceVehicles = vehicles.stream()
                .map(vehicle -> new OccurrenceVehicles(occurrence, vehicle))
                .toList();
        occurrenceVehiclesRepository.saveAll(occurrenceVehicles);

        List<OccurrenceUsers> occurrenceUsers = users.stream()
                .map(user -> new OccurrenceUsers(occurrence, user))
                .toList();
        occurrenceUsersRepository.saveAll(occurrenceUsers);



        var responseDTO = modelMapper.map(occurrence, OccurrenceSecondResponseDTO.class);
        
        responseDTO.setOccurrenceNature(occurrence.getOccurrenceSubType().getOccurrenceType().getNature().getName());
        responseDTO.setOccurrenceType(occurrence.getOccurrenceSubType().getOccurrenceType().getName());
        responseDTO.setOccurrenceSubType(occurrence.getOccurrenceSubType().getName());
        responseDTO.setStatus(occurrenceStatus.getName());
        
        responseDTO.setUsers(users.stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .toList());
        
        responseDTO.setVehicles(vehicles.stream()
                .map(vehicle -> {
                    VehicleResponseDTO vehicleDTO = new VehicleResponseDTO();
                    vehicleDTO.setId(vehicle.getId());
                    vehicleDTO.setActive(vehicle.isActive());
                    vehicleDTO.setName(vehicle.getName());
                    vehicleDTO.setBattalionId(vehicle.getBattalion().getId());
                    vehicleDTO.setBattalionName(vehicle.getBattalion().getName());
                    return vehicleDTO;
                })
                .toList());
        
        return responseDTO;
    }

    @Override
    public OccurrenceSecondResponseDTO getOccurrenceById(Long id) {
        var occurrence = occurrenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));
        var occurrenceStatus = occurrenceStatusRepository.findById(occurrence.getStatus().getId())
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));
        
        var occurrenceUsers = occurrenceUsersRepository.findByOccurrence(occurrence);
        var occurrenceVehicles = occurrenceVehiclesRepository.findByOccurrence(occurrence);

        var responseDTO = modelMapper.map(occurrence, OccurrenceSecondResponseDTO.class);

        responseDTO.setOccurrenceNature(occurrence.getOccurrenceSubType().getOccurrenceType().getNature().getName());
        responseDTO.setOccurrenceType(occurrence.getOccurrenceSubType().getOccurrenceType().getName());
        responseDTO.setOccurrenceSubType(occurrence.getOccurrenceSubType().getName());
        responseDTO.setStatus(occurrenceStatus.getName());

        responseDTO.setUsers(occurrenceUsers.stream()
                .map(occUser -> modelMapper.map(occUser.getUser(), UserResponseDTO.class))
                .toList());

        responseDTO.setVehicles(occurrenceVehicles.stream()
                .map(occVehicle -> {
                    VehicleResponseDTO vehicleDTO = new VehicleResponseDTO();
                    vehicleDTO.setId(occVehicle.getVehicle().getId());
                    vehicleDTO.setActive(occVehicle.getVehicle().isActive());
                    vehicleDTO.setName(occVehicle.getVehicle().getName());
                    vehicleDTO.setBattalionId(occVehicle.getVehicle().getBattalion().getId());
                    vehicleDTO.setBattalionName(occVehicle.getVehicle().getBattalion().getName());
                    return vehicleDTO;
                })
                .toList());

        return responseDTO;
    }

    @Override
    public OccurrenceSecondResponseDTO updateOccurrence(Long id, OccurrenceUpdateDTO occurrenceUpdateDTO) {
        var occurrence = occurrenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));

        var address = modelMapper.map(occurrenceUpdateDTO.getAddress(), Address.class);
        
        var occurrenceSubType = occurrenceSubTypeRepository.findById(occurrenceUpdateDTO.getOccurrenceSubType())
                .orElseThrow(() -> new RuntimeException("Subtipo de ocorrência não encontrado"));
        var occurrenceStatus = occurrenceStatusRepository.findById(occurrenceUpdateDTO.getStatus())
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));

        occurrence.setOccurrenceHasVictims(occurrenceUpdateDTO.isOccurrenceHasVictims());
        occurrence.setOccurrenceRequester(occurrenceUpdateDTO.getOccurrenceRequester());
        occurrence.setOccurrenceRequesterPhoneNumber(occurrenceUpdateDTO.getOccurrenceRequesterPhoneNumber());
        occurrence.setOccurrenceSubType(occurrenceSubType);
        occurrence.setAddress(address);
        occurrence.setOccurrenceDetails(occurrenceUpdateDTO.getOccurrenceDetails());
        occurrence.setLatitude(occurrenceUpdateDTO.getLatitude());
        occurrence.setLongitude(occurrenceUpdateDTO.getLongitude());
        occurrence.setOccurrenceArrivalTime(occurrenceUpdateDTO.getOccurrenceArrivalTime());
        occurrence.setStatus(occurrenceStatus);

        occurrenceRepository.save(occurrence);

        var existingUsers = occurrenceUsersRepository.findByOccurrence(occurrence);
        occurrenceUsersRepository.deleteAll(existingUsers);
        
        var users = userRepository.findAllById(occurrenceUpdateDTO.getUserIds());
        List<OccurrenceUsers> occurrenceUsers = users.stream()
                .map(user -> new OccurrenceUsers(occurrence, user))
                .toList();
        occurrenceUsersRepository.saveAll(occurrenceUsers);

        var existingVehicles = occurrenceVehiclesRepository.findByOccurrence(occurrence);
        occurrenceVehiclesRepository.deleteAll(existingVehicles);
        
        var vehicles = vehicleRepository.findAllById(occurrenceUpdateDTO.getVehicles());
        List<OccurrenceVehicles> occurrenceVehicles = vehicles.stream()
                .map(vehicle -> new OccurrenceVehicles(occurrence, vehicle))
                .toList();
        occurrenceVehiclesRepository.saveAll(occurrenceVehicles);

        var responseDTO = modelMapper.map(occurrence, OccurrenceSecondResponseDTO.class);
        
        responseDTO.setOccurrenceNature(occurrenceSubType.getOccurrenceType().getNature().getName());
        responseDTO.setOccurrenceType(occurrenceSubType.getOccurrenceType().getName());
        responseDTO.setOccurrenceSubType(occurrenceSubType.getName());
        responseDTO.setStatus(occurrenceStatus.getName());
        
        responseDTO.setUsers(users.stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .toList());
        
        responseDTO.setVehicles(vehicles.stream()
                .map(vehicle -> {
                    VehicleResponseDTO vehicleDTO = new VehicleResponseDTO();
                    vehicleDTO.setId(vehicle.getId());
                    vehicleDTO.setActive(vehicle.isActive());
                    vehicleDTO.setName(vehicle.getName());
                    vehicleDTO.setBattalionId(vehicle.getBattalion().getId());
                    vehicleDTO.setBattalionName(vehicle.getBattalion().getName());
                    return vehicleDTO;
                })
                .toList());
        
        return responseDTO;
    }

    @Override
    public ResponseDTO activate(Long id) {
        var occurrence = occurrenceRepository.findById(id).orElse(null);
        if (occurrence == null) {
            return ResponseDTO.erro("Ocorrência não existe");
        }
        if(occurrence.isActive()){
            return ResponseDTO.erro("Ocorrência já está ativa");
        }
        occurrence.setActive(true);
        occurrenceRepository.save(occurrence);
        return ResponseDTO.sucesso("Ocorrência ativada com sucesso");
    }

    @Override
    public ResponseDTO deactivate(Long id) {
        var occurrence = occurrenceRepository.findById(id).orElse(null);

        if (occurrence == null) {
            return ResponseDTO.erro("Ocorrência não existe");
        }
        if(!occurrence.isActive()){
            return ResponseDTO.erro("Ocorrência já está desativada");
        }
        occurrence.setActive(false);
        occurrenceRepository.save(occurrence);
        return ResponseDTO.sucesso("Ocorrência desativada com sucesso");
    }

    @Override
    public PaginatorGeneric<OccurrencePaginatorDTO> GetPaginatorGetPaginatorOccurrence(Pageable pageable, boolean active, String filterGeneric) {
        Page<Occurrence> paginator;
        
        if (filterGeneric != null && !filterGeneric.isEmpty()) {
            paginator = occurrenceRepository.findByActiveAndOccurrenceSubTypeNameContainingIgnoreCase(
                    active, filterGeneric, pageable);
        } else {
            paginator = occurrenceRepository.findByActive(active, pageable);
        }

        List<OccurrencePaginatorDTO> contentList = paginator.getContent().stream()
                .map(o -> {
                    OccurrencePaginatorDTO dto = modelMapper.map(o, OccurrencePaginatorDTO.class);
                    dto.setOccurrenceSubType(o.getOccurrenceSubType().getName());
                    dto.setOccurrenceType(o.getOccurrenceSubType().getOccurrenceType().getName());
                    dto.setOccurrenceNature(o.getOccurrenceSubType().getOccurrenceType().getNature().getName());
                    dto.setStatus(o.getStatus().getName());
                    return dto;
                })
                .collect(Collectors.toList());

        return new PaginatorGeneric<>(
                paginator.getNumber(),
                paginator.getSize(),
                (int) paginator.getTotalElements(),
                contentList
        );
    }


}
