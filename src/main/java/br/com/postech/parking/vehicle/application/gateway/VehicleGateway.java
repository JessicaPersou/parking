package br.com.postech.parking.vehicle.application.gateway;


import br.com.postech.parking.user.domain.User;
import br.com.postech.parking.vehicle.domain.Vehicle;
import java.util.List;
import java.util.Optional;

public interface VehicleGateway {

    Optional<Vehicle> getVehicleByPlate(String plate);

    Optional<Vehicle> findVehicleById(Long id);

    Vehicle createVehicle(Vehicle vehicle, User owner);

    List<Vehicle> getAllVehicles();

    Vehicle updateVehicle(String plate, Vehicle vehicle);

    void deleteVehicle(String plate);
}
