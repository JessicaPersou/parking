package br.com.postech.parking.ticket.adapter.controller;

import br.com.postech.parking.ticket.application.dto.TicketDTO;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.ticket.domain.factory.TicketFactory;
import br.com.postech.parking.ticket.usecase.CreateTicketUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    public final TicketFactory ticketFactory;

    private final CreateTicketUseCase createTicketUseCase;

    @PostMapping
    public ResponseEntity<TicketDTO> createNewUser(@Valid @RequestBody TicketDTO ticketDTO) {
        Ticket ticket = ticketFactory.createTicketDTO(ticketDTO);
        Ticket createTicket = createTicketUseCase.createTicketUseCase(ticket);
        TicketDTO responseDTO = ticketFactory.createTicketDTO(createTicket);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
