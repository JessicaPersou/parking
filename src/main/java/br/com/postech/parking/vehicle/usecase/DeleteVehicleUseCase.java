package br.com.postech.parking.vehicle.usecase;

import br.com.postech.parking.vehicle.application.gateway.VehicleGateway;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteVehicleUseCase {

    private final VehicleGateway vehicleGateway;

    public void delete(String plate) {
        if (plate != null) {
            vehicleGateway.deleteVehicle(plate);
        }
        log.info("Plate not found: {}", plate);
        throw new EntityNotFoundException("Plate not found: " + plate);
    }

}
