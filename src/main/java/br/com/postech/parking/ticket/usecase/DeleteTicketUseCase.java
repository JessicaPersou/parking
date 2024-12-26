package br.com.postech.parking.ticket.usecase;

import br.com.postech.parking.ticket.application.gateway.TicketGateway;
import br.com.postech.parking.ticket.domain.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteTicketUseCase {

    private final TicketGateway ticketGateway;

    public void deleteTicket(Long id) {
        log.info("Delete ticket: {}", id);
        ticketGateway.deleteTicket(id);
    }
}
