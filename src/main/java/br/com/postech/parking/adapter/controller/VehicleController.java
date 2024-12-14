package br.com.postech.parking.adapter.controller;

import br.com.postech.parking.application.dto.VehicleDTO;
import br.com.postech.parking.domain.Vehicle;
import br.com.postech.parking.domain.factory.VehicleFactory;
import br.com.postech.parking.usecases.CreateVehicleUseCase;
import br.com.postech.parking.usecases.DeleteVehicleUseCase;
import br.com.postech.parking.usecases.FindVehicleUseCase;
import br.com.postech.parking.usecases.UpdateVehicleUseCase;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
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

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    /***
     TODO: Métodos HTTP
     * findByPlate (GET by Plate)
     * findAll (GET)
     * updateVehicle (PUT)
     * deleteVehicle (DELETE)
     ***/

    private final CreateVehicleUseCase createVehicleUseCase;
    private final VehicleFactory vehicleFactory;
    private final FindVehicleUseCase findVehicleUseCase;
    private final UpdateVehicleUseCase updateVehicleUseCase;
    private final DeleteVehicleUseCase deleteVehicleUseCase;

    public VehicleController(CreateVehicleUseCase createVehicleUseCase, VehicleFactory vehicleFactory,
            FindVehicleUseCase findVehicleUseCase, UpdateVehicleUseCase updateVehicleUseCase,
            DeleteVehicleUseCase deleteVehicleUseCase) {
        this.createVehicleUseCase = createVehicleUseCase;
        this.vehicleFactory = vehicleFactory;
        this.findVehicleUseCase = findVehicleUseCase;
        this.updateVehicleUseCase = updateVehicleUseCase;
        this.deleteVehicleUseCase = deleteVehicleUseCase;
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleFactory.createVehicle(vehicleDTO);
        Vehicle createdVehicle = createVehicleUseCase.createVehicle(vehicle);
        VehicleDTO responseDTO = vehicleFactory.createVehicleDTO(createdVehicle);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> findVehicle(@PathVariable Long id) {
        Vehicle vehicle = findVehicleUseCase.findVehicle(id);
        VehicleDTO responseDTO = vehicleFactory.createVehicleDTO(vehicle);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> findVehicles() {
        List<Vehicle> vehicles = findVehicleUseCase.findAllVehicles();
        List<VehicleDTO> dtos = vehicles.stream().map(vehicleFactory::createVehicleDTO)
                .collect(Collectors.toUnmodifiableList());
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PutMapping("/{plate}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable String plate,
            @Valid @RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleFactory.createVehicle(vehicleDTO);
        Vehicle updateVehicle = updateVehicleUseCase.updateVehicle(plate, vehicle);
        VehicleDTO responseDTO = vehicleFactory.createVehicleDTO(updateVehicle);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/{plate}")
    public void deleteVehicle(@PathVariable String plate) {
        deleteVehicleUseCase.delete(plate);
    }
}
