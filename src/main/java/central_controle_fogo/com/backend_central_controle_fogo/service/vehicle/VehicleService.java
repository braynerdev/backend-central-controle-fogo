//package central_controle_fogo.com.backend_central_controle_fogo.service.vehicle;
//
//import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
//import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
//import central_controle_fogo.com.backend_central_controle_fogo.dto.patent.PatentResponseDTO;
//import central_controle_fogo.com.backend_central_controle_fogo.dto.vehicle.VehicleRequestDTO;
//import central_controle_fogo.com.backend_central_controle_fogo.dto.vehicle.VehicleResponseDTO;
//import central_controle_fogo.com.backend_central_controle_fogo.model.patent.Patent;
////import central_controle_fogo.com.backend_central_controle_fogo.model.vehicles.Vehicle;
////import central_controle_fogo.com.backend_central_controle_fogo.repository.vehicle.IVehicleRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@Service
//public class VehicleService implements IVehicleService{
////    @Autowired
////    private IVehicleRepository vehicleRepository;
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Override
////    public VehicleResponseDTO createdVehicle(VehicleRequestDTO vehicleRequestDTO) {
////        try{
////            Vehicle vehicle = modelMapper.map(vehicleRequestDTO, Vehicle.class);
////            vehicleRepository.save(vehicle);
////            var Response = modelMapper.map(vehicle,VehicleResponseDTO.class);
////            return Response;
////        }
////        catch(Exception e){
////            return null;
////        }
////    }
//
//    @Override
//    public VehicleResponseDTO updateVehicle(Long id, VehicleRequestDTO vehicleRequestDTO) {
//        try{
//            var repositoryVehicle = vehicleRepository.findById(id).orElse(null);
//            if(repositoryVehicle == null){
//                return null;
//            }
//            modelMapper.map(vehicleRequestDTO, repositoryVehicle);
//            vehicleRepository.save(repositoryVehicle);
//            return modelMapper.map(repositoryVehicle, VehicleResponseDTO.class);
//        }
//        catch(Exception e){
//            return null;
//        }
//    }
//
//    @Override
//    public VehicleResponseDTO getByIdVehicle(Long id) {
//        try{
//            var repositoryVehicle = vehicleRepository.findById(id).orElse(null);
//            if(repositoryVehicle == null){
//                return null;
//            }
//            return modelMapper.map(repositoryVehicle, VehicleResponseDTO.class);
//        }
//        catch(Exception e){
//            return null;
//        }
//    }
//
//    @Override
//    public PaginatorGeneric<VehicleResponseDTO> GetPaginatorVehicle(Pageable pageable, boolean active, String filterGeneric) {
//        try{
//            Page<Vehicle> paginator = vehicleRepository.findAll(pageable);
//
//            List<VehicleResponseDTO> filterList = paginator.stream()
//                    .map(v -> modelMapper.map(v, VehicleResponseDTO.class))
//                    .filter(v -> {
//                        boolean matches = true;
//                        if (filterGeneric != null && !filterGeneric.isEmpty()) {
//                            matches = v.getName().toLowerCase().contains(filterGeneric.toLowerCase());
//                        }
//                        matches &= v.isActive() == active;
//                        return matches;
//                    })
//                    .collect(Collectors.toList());
//
//            var paginatorGeneric = new PaginatorGeneric<VehicleResponseDTO>(
//                    pageable.getPageNumber(),
//                    pageable.getPageSize(),
//                    filterList.size(),
//                    filterList
//            );
//            return paginatorGeneric;
//        }
//        catch(Exception e){
//            return null;
//        }
//    }
//
//    @Override
//    public ResponseDTO DeactivateVehicle(Long id) {
//        try{
//            var repositoryVehicle = vehicleRepository.findById(id).orElse(null);
//            if(repositoryVehicle == null){
//                return ResponseDTO.erro("impossivel desativar veículo!");
//            }
//            if (!repositoryVehicle.isActive()){
//                return ResponseDTO.erro("Veículo já está desativada!");
//            }
//            repositoryVehicle.setActive(false);
//            vehicleRepository.save(repositoryVehicle);
//            return ResponseDTO.sucesso("Veículo desativada com sucesso!");
//        }
//        catch(Exception e){
//            return ResponseDTO.erro("Erro ao desativar Veículo!");
//        }
//    }
//
//    @Override
//    public ResponseDTO ActivateVehicle(Long id) {
//        try{
//            var repositoryVehicle = vehicleRepository.findById(id).orElse(null);
//            if(repositoryVehicle == null){
//                return ResponseDTO.erro("impossivel ativar veículo!");
//            }
//            if (repositoryVehicle.isActive()){
//                return ResponseDTO.erro("Veículo já está ativo!");
//            }
//            repositoryVehicle.setActive(true);
//            vehicleRepository.save(repositoryVehicle);
//            return ResponseDTO.sucesso("Veículo ativado com sucesso!");
//        }
//        catch(Exception e){
//            return ResponseDTO.erro("Erro ao ativar Veículo!");
//        }
//    }
//}
