package br.com.postech.parking.adapter.controller;

import br.com.postech.parking.application.dto.VehicleDTO;
import br.com.postech.parking.domain.Vehicle;
import br.com.postech.parking.domain.factory.VehicleFactory;
import br.com.postech.parking.exception.VehicleAlreadyExistsException;
import br.com.postech.parking.usecases.CreateVehicleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final CreateVehicleUseCase createVehicleUseCase;
    private final VehicleFactory vehicleFactory;

    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleFactory.createVehicle(vehicleDTO);
        Vehicle createdVehicle = createVehicleUseCase.createVehicle(vehicle);
        VehicleDTO responseDTO = vehicleFactory.createVehicleDTO(createdVehicle);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @ExceptionHandler(VehicleAlreadyExistsException.class)
    public ResponseEntity<String> handleVehicleAlreadyExists(VehicleAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
