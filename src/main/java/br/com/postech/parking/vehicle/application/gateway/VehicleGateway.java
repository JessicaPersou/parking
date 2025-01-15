package br.com.postech.parking.vehicle.application.gateway;


import br.com.postech.parking.owner.domain.Owner;
import br.com.postech.parking.vehicle.domain.Vehicle;

import java.util.Optional;

public interface VehicleGateway {

    Optional<Vehicle> getVehicleByPlate(String plate);

    Vehicle createVehicle(Vehicle vehicle, Owner owner);

    Vehicle findById(Long id);
}

