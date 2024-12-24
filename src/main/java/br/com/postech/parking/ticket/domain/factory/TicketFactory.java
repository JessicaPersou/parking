package br.com.postech.parking.ticket.domain.factory;

import br.com.postech.parking.ticket.application.dto.TicketDTO;
import br.com.postech.parking.ticket.domain.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketFactory {

    public Ticket createTicket(TicketDTO dto) {
        return new Ticket(
                dto.id(),
                dto.startTime(),
                dto.endTime(),
                dto.status(),
                dto.price()
        );
    }

    public TicketDTO createTicket(Ticket entity) {
        return new TicketDTO(
                entity.getId(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getStatus(),
                entity.getPrice()
        );
    }
}
