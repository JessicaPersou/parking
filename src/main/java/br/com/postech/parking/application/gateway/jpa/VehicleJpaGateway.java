package br.com.postech.parking.application.gateway.jpa;

import br.com.postech.parking.application.gateway.VehicleGateway;
import br.com.postech.parking.application.gateway.jpa.entity.VehicleEntity;
import br.com.postech.parking.application.gateway.jpa.repository.VehicleRepository;
import br.com.postech.parking.domain.Vehicle;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class VehicleJpaGateway implements VehicleGateway {
    private final VehicleRepository vehicleRepository;

    @Override
    public Optional<Vehicle> getVehicleByPlate(String plate) {
        return vehicleRepository.findByPlate(plate)
                .map(this::convertToVehicle);
    }

    private Vehicle convertToVehicle(VehicleEntity entity) {
        return new Vehicle(
                entity.getId(),
                entity.getPlate(),
                entity.getModel(),
                entity.getColor(),
                entity.getInputDate(),
                entity.getExitDate()
        );
    }
}