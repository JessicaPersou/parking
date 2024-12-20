package br.com.postech.parking.vehicle.usecase;

import br.com.postech.parking.vehicle.application.gateway.VehicleGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteVehicleUseCase {

    private final VehicleGateway vehicleGateway;

    public void delete(String plate) {
        vehicleGateway.deleteVehicle(plate);
    }

}