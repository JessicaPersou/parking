package br.com.postech.parking.ticket.domain.factory;

import br.com.postech.parking.ticket.application.dto.TicketDTO;
import br.com.postech.parking.ticket.domain.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketFactory {

    public Ticket createTicketDTO(TicketDTO dto) {
        return new Ticket(
                dto.id(),
                dto.entryTime(),
                dto.exitTime(),
                dto.status(),
                dto.totalAmount()
        );
    }

    public TicketDTO createTicketDTO(Ticket ticket) {
        return new TicketDTO(
                ticket.getId(),
                ticket.getEntryTime(),
                ticket.getExitTime(),
                ticket.getStatus(),
                ticket.getTotalAmount(),
                ticket.getVehicle() != null ? ticket.getVehicle().getId() : null,
                ticket.getOwner() != null ? ticket.getOwner().getId() : null);
    }

}
