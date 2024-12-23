package br.com.postech.parking.vehicle.application.gateway.jpa;

import br.com.postech.parking.exception.EntityNotFoundException;
import br.com.postech.parking.vehicle.application.dto.VehicleDTO;
import br.com.postech.parking.vehicle.application.gateway.VehicleGateway;
import br.com.postech.parking.vehicle.application.gateway.jpa.entity.VehicleEntity;
import br.com.postech.parking.vehicle.application.gateway.jpa.repository.VehicleRepository;
import br.com.postech.parking.vehicle.domain.Vehicle;
import br.com.postech.parking.vehicle.domain.factory.VehicleFactory;
import br.com.postech.parking.vehicle.domain.valueobject.VehiclePlate;
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

        VehicleDTO dto = vehicleFactory.createVehicleDTO(vehicle);
        VehicleEntity entityToSave = dto.toVehicleEntity();

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
        log.info("Delete vehicle: {}", vehicleExits);
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