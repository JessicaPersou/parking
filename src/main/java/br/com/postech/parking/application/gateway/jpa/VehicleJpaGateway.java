package br.com.postech.parking.application.gateway.jpa;

import br.com.postech.parking.application.dto.VehicleDTO;
import br.com.postech.parking.application.gateway.VehicleGateway;
import br.com.postech.parking.application.gateway.jpa.entity.VehicleEntity;
import br.com.postech.parking.application.gateway.jpa.repository.VehicleRepository;
import br.com.postech.parking.domain.Vehicle;
import br.com.postech.parking.exception.EntityAlreadyExistsException;
import br.com.postech.parking.exception.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        log.info("Creating vehicle: {}", vehicle);

        Optional<VehicleEntity> existingVehicle = vehicleRepository.findByPlate(vehicle.getPlate());
        if (existingVehicle.isPresent()) {
            log.info("Vehicle with plate {} already exists", vehicle.getPlate());
            throw new EntityAlreadyExistsException("Vehicle with plate: " + vehicle.getPlate() + " already exists");
        }
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

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehiclesList = vehicleRepository.findAll()
                .stream()
                .map(this::convertToVehicle)
                .collect(Collectors.toList());

        log.info("Quantity of vehicles: {}", vehicleRepository.count());

        return vehiclesList;
    }

    @Override
    public Vehicle updateVehicle(String plate, Vehicle vehicle) {

        VehicleEntity vehicleExits = vehicleRepository.findByPlate(plate)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with plate: " + plate));

        vehicleExits.setId(vehicle.getId());
        vehicleExits.setColor(vehicle.getColor());
        vehicleExits.setExitDate(vehicle.getExitDate());

        vehicleRepository.save(vehicleExits);
        log.info("Update vehicle: {}", vehicleExits);
        return convertToVehicle(vehicleExits);
    }

    @Override
    public void deleteVehicle(String plate) {
        VehicleEntity vehicleExits = vehicleRepository.findByPlate(plate)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with plate: " + plate));
        vehicleRepository.delete(vehicleExits);
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