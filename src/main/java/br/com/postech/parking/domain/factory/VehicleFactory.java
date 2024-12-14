package br.com.postech.parking.domain.factory;


import br.com.postech.parking.application.dto.VehicleDTO;
import br.com.postech.parking.domain.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleFactory {

    public Vehicle createVehicle(VehicleDTO vehicleDTO) {
        return new Vehicle(
                vehicleDTO.id(),
                vehicleDTO.plate(),
                vehicleDTO.model(),
                vehicleDTO.color(),
                vehicleDTO.inputDate(),
                vehicleDTO.exitDate()
        );
    }

    public VehicleDTO createVehicleDTO(Vehicle vehicle) {
        return new VehicleDTO(
                vehicle.getId(),
                vehicle.getPlate(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getInputDate(),
                vehicle.getExitDate()
        );
    }
}


