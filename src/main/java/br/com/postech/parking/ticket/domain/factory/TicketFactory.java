package br.com.postech.parking.ticket.domain.factory;

import br.com.postech.parking.ticket.application.dto.TicketDTO;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.user.domain.User;
import br.com.postech.parking.vehicle.domain.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class TicketFactory {

    public Ticket createTicketDTO(TicketDTO dto) {
        return new Ticket(
                dto.id(),
                dto.startTime(),
                dto.endTime(),
                dto.status(),
                dto.price()
        );
    }

    public TicketDTO createTicketDTO(Ticket ticket) {
        return new TicketDTO(
                ticket.getId(),
                ticket.getStartTime(),
                ticket.getEndTime(),
                ticket.getStatus(),
                ticket.getPrice(),
                ticket.getVehicle() != null ? ticket.getVehicle().getId() : null,
                ticket.getUser() != null ? ticket.getUser().getId() : null        );
    }

    public Ticket createTicketFromEntities(TicketDTO dto, Vehicle vehicle, User user) {
        Ticket ticket = new Ticket(
                dto.id(),
                dto.startTime(),
                dto.endTime(),
                dto.status(),
                dto.price()
        );
        ticket.setVehicle(vehicle);
        ticket.setUser(user);
        return ticket;
    }
}
