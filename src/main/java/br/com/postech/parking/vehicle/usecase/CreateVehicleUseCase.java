package br.com.postech.parking.vehicle.usecase;

import br.com.postech.parking.exception.EntityAlreadyExistsException;
import br.com.postech.parking.exception.EntityNotFoundException;
import br.com.postech.parking.user.application.gateway.UserGateway;
import br.com.postech.parking.user.domain.User;
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
    private final UserGateway userGateway;

    public Vehicle createVehicle(Vehicle vehicle, Long userId) {
        User validUser = userGateway.findUserById(userId).orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        vehicle.setUserId(validUser);

        Optional<Vehicle> existingVehicle = vehicleGateway.getVehicleByPlate(vehicle.getPlate().getValue());

        if (existingVehicle.isPresent()) {
            log.info("Vehicle with plate {} already exists, with another user.", vehicle.getPlate().getValue());
            throw new EntityAlreadyExistsException(
                    "Vehicle with plate: " + vehicle.getPlate().getValue() + " already exists, with another user.");
        }

        log.info("Validations of dates to create Vehicle: {}", vehicle);

        return vehicleGateway.createVehicle(vehicle, validUser);
    }
}