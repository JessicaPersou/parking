package br.com.postech.parking.vehicle.adapter.controller;

import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.vehicle.application.dto.VehicleDTO;
import br.com.postech.parking.vehicle.domain.Vehicle;
import br.com.postech.parking.vehicle.domain.factory.VehicleFactory;
import br.com.postech.parking.vehicle.usecase.CreateVehicleUseCase;
import br.com.postech.parking.vehicle.usecase.DeleteVehicleUseCase;
import br.com.postech.parking.vehicle.usecase.FindVehicleUseCase;
import br.com.postech.parking.vehicle.usecase.UpdateVehicleUseCase;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final FindVehicleUseCase findVehicleUseCase;
    private final UpdateVehicleUseCase updateVehicleUseCase;
    private final DeleteVehicleUseCase deleteVehicleUseCase;

    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleFactory.createVehicle(vehicleDTO);
        Vehicle createdVehicle = createVehicleUseCase.createVehicle(vehicle, vehicleDTO.userId());
        VehicleDTO responseDTO = new VehicleDTO(
                createdVehicle.getId(),
                createdVehicle.getPlate().getValue(),
                createdVehicle.getModel(),
                createdVehicle.getColor(),
                vehicleDTO.userId(),
                createdVehicle.getTickets().stream().map(Ticket::getId).toList()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> findVehicle(@PathVariable Long id) {
        Vehicle vehicle = findVehicleUseCase.findVehicle(id);
        VehicleDTO responseDTO = vehicleFactory.createVehicleDTO(vehicle);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> findAllVehicles() {
        List<Vehicle> vehicles = findVehicleUseCase.findAllVehicles();

        List<VehicleDTO> dtos = vehicles.stream()
                .map(vehicle -> {
                    if (vehicle.getUser() == null) {
                        log.warn("Vehicle {} has no associated user", vehicle.getId());
                    }

                    return new VehicleDTO(
                            vehicle.getId(),
                            vehicle.getPlate().getValue(),
                            vehicle.getModel(),
                            vehicle.getColor(),
                            vehicle.getUser().getId(),
                            vehicle.getTickets().stream().map(Ticket::getId).toList()
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PutMapping("/{plate}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable String plate,
            @Valid @RequestBody VehicleDTO vehicleDTO) {
        Vehicle updateVehicle = updateVehicleUseCase.updateVehicle(plate, vehicleFactory.createVehicle(vehicleDTO));
        VehicleDTO responseDTO = vehicleFactory.createVehicleDTO(updateVehicle);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/{plate}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String plate) {
        deleteVehicleUseCase.delete(plate);
        return ResponseEntity.noContent().build();
    }
}
