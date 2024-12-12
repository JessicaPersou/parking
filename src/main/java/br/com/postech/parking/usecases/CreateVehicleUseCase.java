package br.com.postech.parking.usecases;

import br.com.postech.parking.application.dto.VehicleDTO;
import br.com.postech.parking.application.gateway.VehicleGateway;
import br.com.postech.parking.application.gateway.jpa.entity.VehicleEntity;
import br.com.postech.parking.application.gateway.jpa.repository.VehicleRepository;
import br.com.postech.parking.domain.Vehicle;
import br.com.postech.parking.domain.factory.VehicleFactory;
import br.com.postech.parking.exception.VehicleAlreadyExistsException;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateVehicleUseCase {
    private final VehicleGateway vehicleGateway;
    private final VehicleRepository vehicleRepository;

    public Vehicle createVehicle(Vehicle vehicle) {
        log.info("Creating vehicle: {}", vehicle);

        Optional<Vehicle> existingVehicle = vehicleGateway.getVehicleByPlate(vehicle.getPlate());
        if (existingVehicle.isPresent()) {
            throw new VehicleAlreadyExistsException("Vehicle with plate " + vehicle.getPlate() + " already exists");
        }

        VehicleEntity vehicleEntity = new VehicleDTO(
                vehicle.getId(),
                vehicle.getPlate(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getInputDate(),
                vehicle.getExitDate()
        ).toEntity();

        if (vehicleEntity.getInputDate() == null) {
            vehicleEntity.setInputDate(LocalDateTime.now());
        }

        VehicleEntity savedEntity = vehicleRepository.save(vehicleEntity);

        log.info("Save vehicle: {}", savedEntity);

        return new Vehicle(
                savedEntity.getId(),
                savedEntity.getPlate(),
                savedEntity.getModel(),
                savedEntity.getColor(),
                savedEntity.getInputDate(),
                savedEntity.getExitDate()
        );
    }
}