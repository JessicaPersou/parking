package br.com.postech.parking.domain.factory;


import br.com.postech.parking.application.dto.VehicleDTO;
import br.com.postech.parking.application.gateway.jpa.entity.VehicleEntity;
import br.com.postech.parking.domain.Vehicle;
import br.com.postech.parking.domain.valueobject.VehiclePlate;
import org.springframework.stereotype.Component;

@Component
public class VehicleFactory {
    public Vehicle createVehicle(VehicleDTO vehicleDTO) {
        return new Vehicle(
                vehicleDTO.id(),
                VehiclePlate.createVehiclePlateFactory(vehicleDTO.plate()),
                vehicleDTO.model(),
                vehicleDTO.color(),
                vehicleDTO.inputDate(),
                vehicleDTO.exitDate()
        );
    }

    public VehicleDTO createVehicleDTO(Vehicle vehicle) {
        return new VehicleDTO(
                vehicle.getId(),
                vehicle.getPlate().getValue(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getInputDate(),
                vehicle.getExitDate()
        );
    }

    public VehicleDTO createVehicleDTO(VehicleEntity vehicleEntity) {
        return VehicleDTO.fromEntity(vehicleEntity);
    }

    public VehicleEntity createVehicleEntity(Vehicle vehicle) {
        return VehicleDTO.fromVehicle(vehicle).toEntity();
    }
}

