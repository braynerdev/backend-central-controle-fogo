package central_controle_fogo.com.backend_central_controle_fogo.controller;

import central_controle_fogo.com.backend_central_controle_fogo.dto.battalion.BattalionRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.battalion.Battalion;
import central_controle_fogo.com.backend_central_controle_fogo.service.battalion.BattalionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("battalion")
@Tag(name = "battalion", description = "Operações relacionadas a batalhão")
public class BattalionController {

    @Autowired
    private BattalionService battalionService;


    @PostMapping(value = "/created")
    @Operation(summary = "criar batalhão")
    public ResponseEntity createBattalion(@Valid @RequestBody BattalionRequestDTO battalionRequestDTO ) {
        try {
            if (battalionRequestDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            var serviceBattalion = battalionService.CreateBattalion(battalionRequestDTO);

            if (!serviceBattalion.isSucesso()) {
                return new ResponseEntity<>(serviceBattalion.getMensagem(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(serviceBattalion.getMensagem(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
