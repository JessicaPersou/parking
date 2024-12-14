package br.com.postech.parking.usecases;

import br.com.postech.parking.application.gateway.VehicleGateway;
import br.com.postech.parking.domain.Vehicle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateVehicleUseCase {

    private final VehicleGateway vehicleGateway;

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleGateway.createVehicle(vehicle);
    }
}