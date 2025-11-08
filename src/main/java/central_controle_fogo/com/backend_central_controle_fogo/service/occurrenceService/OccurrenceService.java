package central_controle_fogo.com.backend_central_controle_fogo.service.occurrenceService;


import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.UserResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceFirst.OccurrenceFirstRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceFirst.OccurrenceFirstResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceSecond.OccurrenceSecondRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceSecond.OccurrenceSecondResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.vehicle.VehicleResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceUsers;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.Occurrence;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceVehicles;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IRepositoryUser;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.*;
import central_controle_fogo.com.backend_central_controle_fogo.repository.vehicle.IVehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}