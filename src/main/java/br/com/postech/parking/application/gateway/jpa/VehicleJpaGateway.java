package br.com.postech.parking.application.gateway.jpa;

import br.com.postech.parking.application.dto.VehicleDTO;
import br.com.postech.parking.application.gateway.VehicleGateway;
import br.com.postech.parking.application.gateway.jpa.entity.VehicleEntity;
import br.com.postech.parking.application.gateway.jpa.repository.VehicleRepository;
import br.com.postech.parking.domain.Vehicle;
import java.time.LocalDateTime;
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

    @Override
    public Optional<Vehicle> findVehicleById(Long id) {
        return vehicleRepository.findById(id).map(this::convertToVehicle);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        VehicleEntity entityToSave = new VehicleDTO(
                vehicle.getId(),
                vehicle.getPlate(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getInputDate(),
                vehicle.getExitDate()
        ).toEntity();

        if (entityToSave.getInputDate() == null) {
            entityToSave.setInputDate(LocalDateTime.now());
        }

        VehicleEntity savedEntity = vehicleRepository.save(entityToSave);
        log.info("Save vehicle: {}", savedEntity);

        return convertToVehicle(savedEntity);
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