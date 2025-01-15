package br.com.postech.parking.vehicle.application.gateway.jpa.repository;

import br.com.postech.parking.vehicle.application.gateway.jpa.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    Optional<VehicleEntity> findByPlate(String plate);

}
