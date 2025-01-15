package br.com.postech.parking.vehicle.adapter.controller;

import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.vehicle.application.dto.VehicleDTO;
import br.com.postech.parking.vehicle.domain.Vehicle;
import br.com.postech.parking.vehicle.domain.factory.VehicleFactory;
import br.com.postech.parking.vehicle.usecase.CreateVehicleUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final CreateVehicleUseCase createVehicleUseCase;
    private final VehicleFactory vehicleFactory;

    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleFactory.createVehicle(vehicleDTO);
        Vehicle createdVehicle = createVehicleUseCase.createVehicle(vehicle, vehicleDTO.ownerId());
        VehicleDTO responseDTO = new VehicleDTO(
                createdVehicle.getId(),
                createdVehicle.getPlate().getValue(),
                createdVehicle.getModel(),
                createdVehicle.getColor(),
                vehicleDTO.ownerId(),
                createdVehicle.getTickets().stream().map(Ticket::getId).toList()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


}
