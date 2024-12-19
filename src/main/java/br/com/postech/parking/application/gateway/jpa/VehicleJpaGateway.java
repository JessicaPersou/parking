package br.com.postech.parking.application.gateway.jpa;

import br.com.postech.parking.application.dto.VehicleDTO;
import br.com.postech.parking.application.gateway.VehicleGateway;
import br.com.postech.parking.application.gateway.jpa.entity.VehicleEntity;
import br.com.postech.parking.application.gateway.jpa.repository.VehicleRepository;
import br.com.postech.parking.domain.Vehicle;
import br.com.postech.parking.domain.factory.VehicleFactory;
import br.com.postech.parking.domain.valueobject.VehiclePlate;
import br.com.postech.parking.exception.EntityAlreadyExistsException;
import br.com.postech.parking.exception.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VehicleJpaGateway implements VehicleGateway {

    private final VehicleRepository vehicleRepository;
    private final VehicleFactory vehicleFactory;

    public VehicleJpaGateway(VehicleRepository vehicleRepository, VehicleFactory vehicleFactory) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleFactory = vehicleFactory;
    }

    @Override
    public Optional<Vehicle> getVehicleByPlate(String plate) {
        return vehicleRepository.findByPlate(plate)
                .map(this::convertToVehicleEntity);
    }

    @Override
    public Optional<Vehicle> findVehicleById(Long id) {
        return vehicleRepository.findById(id).map(this::convertToVehicleEntity);
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        log.info("Creating vehicle: {}", vehicle);

        Optional<VehicleEntity> existingVehicle = vehicleRepository.findByPlate(vehicle.getPlate().getValue());
        if (existingVehicle.isPresent()) {
            log.info("Vehicle with plate {} already exists", vehicle.getPlate().getValue());
            throw new EntityAlreadyExistsException("Vehicle with plate: " + vehicle.getPlate().getValue() + " already exists");
        }
        VehicleDTO dto = vehicleFactory.createVehicleDTO(vehicle);
        VehicleEntity entityToSave = dto.toEntity();

        if (entityToSave.getInputDate() == null) {
            entityToSave.setInputDate(LocalDateTime.now());
        }

        VehicleEntity savedEntity = vehicleRepository.save(entityToSave);
        log.info("Save vehicle: {}", savedEntity);

        return convertToVehicleEntity(savedEntity);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehiclesList = vehicleRepository.findAll()
                .stream()
                .map(this::convertToVehicleEntity)
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
        return convertToVehicleEntity(vehicleExits);
    }

    @Override
    public void deleteVehicle(String plate) {
        VehicleEntity vehicleExits = vehicleRepository.findByPlate(plate)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with plate: " + plate));
        vehicleRepository.delete(vehicleExits);
    }

    private Vehicle convertToVehicleEntity(VehicleEntity entity) {
        return new Vehicle(
                entity.getId(),
                VehiclePlate.createVehiclePlateFactory(entity.getPlate()),
                entity.getModel(),
                entity.getColor(),
                entity.getInputDate(),
                entity.getExitDate()
        );
    }
}