package br.com.postech.parking.usecases;

import br.com.postech.parking.application.gateway.VehicleGateway;
import br.com.postech.parking.domain.Vehicle;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindVehicleUseCase {

    private final VehicleGateway vehicleGateway;

    public Vehicle findVehicle(Long id) {
        return vehicleGateway.findVehicleById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
    }
}
