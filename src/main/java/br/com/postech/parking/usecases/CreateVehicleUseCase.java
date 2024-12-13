package br.com.postech.parking.usecases;

import br.com.postech.parking.application.gateway.VehicleGateway;
import br.com.postech.parking.domain.Vehicle;
import br.com.postech.parking.exception.EntityAlreadyExistsException;
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
        log.info("Creating vehicle: {}", vehicle);

        Optional<Vehicle> existingVehicle = vehicleGateway.getVehicleByPlate(vehicle.getPlate());
        if (existingVehicle.isPresent()) {
            log.info("Vehicle with plate {} already exists", vehicle.getPlate());
            throw new EntityAlreadyExistsException("Vehicle with plate: " + vehicle.getPlate() + " already exists");
        }

        return vehicleGateway.createVehicle(vehicle);
    }
}