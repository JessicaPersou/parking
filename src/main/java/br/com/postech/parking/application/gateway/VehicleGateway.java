package br.com.postech.parking.application.gateway;


import br.com.postech.parking.domain.Vehicle;
import java.util.Optional;

public interface VehicleGateway {

    Optional<Vehicle> getVehicleByPlate(String plate);

}
