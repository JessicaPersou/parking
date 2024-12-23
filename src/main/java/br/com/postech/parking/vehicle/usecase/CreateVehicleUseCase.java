package br.com.postech.parking.vehicle.usecase;

import br.com.postech.parking.exception.EntityAlreadyExistsException;
import br.com.postech.parking.vehicle.application.gateway.VehicleGateway;
import br.com.postech.parking.vehicle.domain.Vehicle;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateVehicleUseCase {

    private final VehicleGateway vehicleGateway;

    public Vehicle createVehicle(Vehicle vehicle) {
        Optional<Vehicle> existingVehicle = vehicleGateway.getVehicleByPlate(vehicle.getPlate().getValue());
        if (existingVehicle.isPresent()) {
            log.info("Vehicle with plate {} already exists, with another user.", vehicle.getPlate().getValue());
            throw new EntityAlreadyExistsException(
                    "Vehicle with plate: " + vehicle.getPlate().getValue() + " already exists, with another user.");
        }

        log.info("Validations of dates to create Vehicle: {}", vehicle);
        vehicle.initializeInputDateIfNull();
        vehicle.validateExitDate(vehicle.getInputDate(), vehicle.getExitDate());
        return vehicleGateway.createVehicle(vehicle);
    }
}