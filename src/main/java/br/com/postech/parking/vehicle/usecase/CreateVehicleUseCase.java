package br.com.postech.parking.vehicle.usecase;

import br.com.postech.parking.exception.EntityAlreadyExistsException;
import br.com.postech.parking.owner.application.gateway.OwnerGateway;
import br.com.postech.parking.owner.domain.Owner;
import br.com.postech.parking.vehicle.application.gateway.VehicleGateway;
import br.com.postech.parking.vehicle.domain.Vehicle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateVehicleUseCase {

    private final VehicleGateway vehicleGateway;
    private final OwnerGateway ownerGateway;

    public Vehicle createVehicle(Vehicle vehicle, Long ownerId) {
        Owner validOwner = ownerGateway.findOwnerById(ownerId);

        vehicle.setOwnerId(validOwner);

        Optional<Vehicle> existingVehicle = vehicleGateway.getVehicleByPlate(vehicle.getPlate().getValue());

        if (existingVehicle.isPresent()) {
            log.info("Vehicle with plate {} already exists, with another owner.", vehicle.getPlate().getValue());
            throw new EntityAlreadyExistsException(
                    "Vehicle with plate: " + vehicle.getPlate().getValue() + " already exists, with another owner.");
        }

        log.info("Validations of dates to create Vehicle: {}", vehicle);

        return vehicleGateway.createVehicle(vehicle, validOwner);
    }
}