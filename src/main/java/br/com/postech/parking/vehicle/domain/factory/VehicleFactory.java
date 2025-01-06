package br.com.postech.parking.vehicle.domain.factory;


import br.com.postech.parking.ticket.application.gateway.jpa.entity.TicketEntity;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.vehicle.application.dto.VehicleDTO;
import br.com.postech.parking.vehicle.application.gateway.jpa.entity.VehicleEntity;
import br.com.postech.parking.vehicle.domain.Vehicle;
import br.com.postech.parking.vehicle.domain.valueobject.VehiclePlate;
import org.springframework.stereotype.Component;

@Component
public class VehicleFactory {

    public Vehicle createVehicle(VehicleDTO dto) {
        return new Vehicle(
                dto.id(),
                VehiclePlate.createVehiclePlateFactory(dto.plate()),
                dto.model(),
                dto.color()
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

