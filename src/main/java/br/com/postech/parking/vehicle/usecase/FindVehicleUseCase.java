package br.com.postech.parking.vehicle.usecase;

import br.com.postech.parking.vehicle.application.gateway.VehicleGateway;
import br.com.postech.parking.vehicle.domain.Vehicle;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindVehicleUseCase {

    private final VehicleGateway vehicleGateway;

    public Vehicle findVehicle(Long id) {
        log.info("Find vehicle with id {}", id);
        return vehicleGateway.findVehicleById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
    }

    public List<Vehicle> findAllVehicles() {
        return vehicleGateway.getAllVehicles();
    }
}
