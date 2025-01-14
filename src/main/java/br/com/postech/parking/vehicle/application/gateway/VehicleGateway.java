package br.com.postech.parking.vehicle.application.gateway;


import br.com.postech.parking.user.domain.User;
import br.com.postech.parking.vehicle.domain.Vehicle;
import java.util.Optional;

public interface VehicleGateway {

    Optional<Vehicle> getVehicleByPlate(String plate);

    Vehicle createVehicle(Vehicle vehicle, User owner);
}

