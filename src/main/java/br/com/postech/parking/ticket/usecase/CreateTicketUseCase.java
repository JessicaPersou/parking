package br.com.postech.parking.ticket.usecase;

import br.com.postech.parking.exception.CalculationErrorException;
import br.com.postech.parking.exception.InvalidOperationException;
import br.com.postech.parking.ticket.application.gateway.TicketGateway;
import br.com.postech.parking.ticket.domain.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateTicketUseCase {

    private final TicketGateway ticketGateway;

    public Ticket createTicketUseCase(Ticket ticket) {
        log.info("Creating ticket: {}", ticket);

        if(ticket.isExpired()){
            log.info("Ticket is expired");
            throw new InvalidOperationException("Ticket is expired");
        }

        if(!ticket.priceIsValid(ticket.getPrice())){
            log.info("Ticket price is invalid");
            throw new CalculationErrorException("Ticket price is invalid");
        }

        ticketGateway.generateTicket(ticket);
        log.info("Ticket created: {}", ticket);
        return ticket;
    }
}
