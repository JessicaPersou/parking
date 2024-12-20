package br.com.postech.parking.usecase;

import br.com.postech.parking.application.gateway.VehicleGateway;
import br.com.postech.parking.domain.Vehicle;
import br.com.postech.parking.domain.factory.VehicleFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateVehicleUseCase {

    private final VehicleGateway vehicleGateway;

    public Vehicle createVehicle(Vehicle vehicle) {
        log.info("Validations of dates to create Vehicle: {}", vehicle);
        vehicle.initializeInputDateIfNull();
        vehicle.validateExitDate(vehicle.getInputDate(), vehicle.getExitDate());
        return vehicleGateway.createVehicle(vehicle);
    }
}