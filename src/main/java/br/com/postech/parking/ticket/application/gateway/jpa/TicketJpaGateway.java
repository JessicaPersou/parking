package br.com.postech.parking.ticket.application.gateway.jpa;

import br.com.postech.parking.ticket.application.dto.TicketDTO;
import br.com.postech.parking.ticket.application.gateway.TicketGateway;
import br.com.postech.parking.ticket.application.gateway.jpa.entity.TicketEntity;
import br.com.postech.parking.ticket.application.gateway.jpa.repository.TicketRepository;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.ticket.domain.factory.TicketFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TicketJpaGateway implements TicketGateway {

    private final TicketRepository ticketRepository;
    private final TicketFactory ticketFactory;

    @Override
    public Ticket generateTicket(Ticket ticket) {
        TicketDTO dto = ticketFactory.createTicketDTO(ticket);
        TicketEntity entity = dto.toTicketEntity();

        ticketRepository.save(entity);
        return convertToUserEntity(entity);
    }

    public Ticket convertToUserEntity(TicketEntity entity) {
        return new Ticket(
                entity.getId(),
                entity.getEntryTime(),
                entity.getExitTime(),
                entity.getStatus(),
                entity.getTotalAmount());
    }
}
