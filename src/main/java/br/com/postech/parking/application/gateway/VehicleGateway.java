package br.com.postech.parking.application.gateway;


import br.com.postech.parking.domain.Vehicle;
import java.util.List;
import java.util.Optional;

public interface VehicleGateway {

    Optional<Vehicle> getVehicleByPlate(String plate);
    Optional<Vehicle> findVehicleById(Long id);
    Vehicle createVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
}
