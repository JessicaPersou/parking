package br.com.postech.parking.vehicle.domain.factory;


import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.vehicle.application.dto.VehicleDTO;
import br.com.postech.parking.vehicle.domain.Vehicle;
import br.com.postech.parking.vehicle.domain.valueobject.VehiclePlate;
import org.springframework.stereotype.Component;

@Component
public class VehicleFactory {

    public Vehicle createVehicle(VehicleDTO vehicleDTO) {
        return new Vehicle(
                vehicleDTO.id(),
                VehiclePlate.createVehiclePlateFactory(vehicleDTO.plate()),
                vehicleDTO.model(),
                vehicleDTO.color()
        );
    }

    public VehicleDTO createVehicleDTO(Vehicle vehicle) {
        return new VehicleDTO(
                vehicle.getId(),
                vehicle.getPlate().getValue(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getUser().getId(),
                vehicle.getTickets().stream().map(Ticket::getId).toList()
        );
    }
}

