package br.com.postech.parking.vehicle.application.gateway.jpa;

import br.com.postech.parking.exception.EntityNotFoundException;
import br.com.postech.parking.owner.application.gateway.jpa.entity.OwnerEntity;
import br.com.postech.parking.owner.application.gateway.jpa.repository.OwnerRepository;
import br.com.postech.parking.owner.domain.Owner;
import br.com.postech.parking.vehicle.application.dto.VehicleDTO;
import br.com.postech.parking.vehicle.application.gateway.VehicleGateway;
import br.com.postech.parking.vehicle.application.gateway.jpa.entity.VehicleEntity;
import br.com.postech.parking.vehicle.application.gateway.jpa.repository.VehicleRepository;
import br.com.postech.parking.vehicle.domain.Vehicle;
import br.com.postech.parking.vehicle.domain.factory.VehicleFactory;
import br.com.postech.parking.vehicle.domain.valueobject.VehiclePlate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class VehicleJpaGateway implements VehicleGateway {

    private final VehicleRepository vehicleRepository;
    private final OwnerRepository ownerRepository;
    private final VehicleFactory vehicleFactory;

    @Override
    public Optional<Vehicle> getVehicleByPlate(String plate) {
        return vehicleRepository.findByPlate(plate)
                .map(this::convertToVehicleEntity);
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle, Owner owner) {
        log.info("Creating vehicle: {}", vehicle);

        VehicleDTO dto = vehicleFactory.createVehicleDTO(vehicle);
        VehicleEntity entityToSave = dto.toVehicleEntity();

        OwnerEntity ownerEntity = ownerRepository.findById(owner.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + owner.getId()));

        entityToSave.setOwner(ownerEntity);

        log.info("Entity to save vehicle: {}", entityToSave);
        return convertToVehicleEntity(vehicleRepository.save(entityToSave));
    }

    public Vehicle findById(Long id) {
        VehicleEntity existVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return convertToVehicleEntity(existVehicle);
    }

    private Vehicle convertToVehicleEntity(VehicleEntity entity) {
        return new Vehicle(
                entity.getId(),
                VehiclePlate.createVehiclePlateFactory(entity.getPlate()),
                entity.getModel(),
                entity.getColor()
        );
    }
}