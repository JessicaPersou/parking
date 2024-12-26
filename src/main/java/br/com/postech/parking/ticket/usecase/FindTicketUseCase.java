package br.com.postech.parking.ticket.usecase;

import br.com.postech.parking.ticket.application.gateway.TicketGateway;
import br.com.postech.parking.ticket.domain.Ticket;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindTicketUseCase {

    private final TicketGateway ticketGateway;

    public Ticket findTicket(Long id) {
        log.info("Finding ticket by ID: {}", id);
        return ticketGateway.getTicket(id).orElseThrow(() -> new EntityNotFoundException("Ticket not found: " + id));
    }

    public List<Ticket> findAllTickets() {
        log.info("Finding all tickets");
        return ticketGateway.allTickets();
    }

}
