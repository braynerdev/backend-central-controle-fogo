//package central_controle_fogo.com.backend_central_controle_fogo.service.vehicle;
//
//import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
//import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
//import central_controle_fogo.com.backend_central_controle_fogo.dto.patent.PatentResponseDTO;
//import central_controle_fogo.com.backend_central_controle_fogo.dto.patent.PatentResquestDTO;
//import central_controle_fogo.com.backend_central_controle_fogo.dto.vehicle.VehicleRequestDTO;
//import central_controle_fogo.com.backend_central_controle_fogo.dto.vehicle.VehicleResponseDTO;
//import org.springframework.data.domain.Pageable;
//
//public interface IVehicleService {
//    VehicleResponseDTO createdVehicle(VehicleRequestDTO vehicleRequestDTO);
//    VehicleResponseDTO updateVehicle(Long id, VehicleRequestDTO vehicleRequestDTO);
//    VehicleResponseDTO getByIdVehicle(Long id);
//    PaginatorGeneric<VehicleResponseDTO> GetPaginatorVehicle(Pageable pageable, boolean active, String filterGeneric);
//    ResponseDTO DeactivateVehicle(Long id);
//    ResponseDTO ActivateVehicle(Long id);
//}
