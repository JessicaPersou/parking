package br.com.postech.parking.application.gateway.jpa.repository;

import br.com.postech.parking.application.gateway.jpa.entity.VehicleEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    Optional<VehicleEntity> findByPlate(String plate);

}
