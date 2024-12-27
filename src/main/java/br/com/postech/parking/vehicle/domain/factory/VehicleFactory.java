package br.com.postech.parking.vehicle.domain.factory;


import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.vehicle.application.dto.VehicleDTO;
import br.com.postech.parking.vehicle.application.gateway.jpa.entity.VehicleEntity;
import br.com.postech.parking.vehicle.domain.Vehicle;
import br.com.postech.parking.vehicle.domain.valueobject.VehiclePlate;
import java.util.stream.Collectors;
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
                vehicle.getExitDate(),
                vehicle.getOwner() != null ? vehicle.getOwner().getId() : null,
                vehicle.getTickets().stream().map(Ticket::getId).toList()
        );
    }
}

