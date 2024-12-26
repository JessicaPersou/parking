package br.com.postech.parking.ticket.usecase;

import br.com.postech.parking.ticket.application.gateway.TicketGateway;
import br.com.postech.parking.ticket.domain.Ticket;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateTicketUseCase {

    private final TicketGateway ticketGateway;

    public Ticket updateTicket(Long id, Ticket ticket) {
        if (id == null || ticket == null) {
            log.info("Ticket is invalid: {} ", ticket);
            throw new EntityNotFoundException("Invalid ticket");
        }
        return ticketGateway.updateTicket(id, ticket);
    }
}
