package br.com.postech.parking.ticket.adapter.controller;

import br.com.postech.parking.ticket.application.dto.TicketDTO;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.ticket.domain.factory.TicketFactory;
import br.com.postech.parking.ticket.usecase.CreateTicketUseCase;
import br.com.postech.parking.ticket.usecase.DeleteTicketUseCase;
import br.com.postech.parking.ticket.usecase.FindTicketUseCase;
import br.com.postech.parking.ticket.usecase.UpdateTicketUseCase;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    public final TicketFactory ticketFactory;

    private final CreateTicketUseCase createTicketUseCase;
//    private final FindTicketUseCase findTicketUseCase;
//    private final UpdateTicketUseCase updateTicketUseCase;
//    private final DeleteTicketUseCase deleteTicketUseCase;

    @PostMapping
    public ResponseEntity<TicketDTO> createNewUser(@Valid @RequestBody TicketDTO ticketDTO) {
        Ticket ticket = ticketFactory.createTicketDTO(ticketDTO);
        Ticket createTicket = createTicketUseCase.createTicketUseCase(ticket);
        TicketDTO responseDTO = ticketFactory.createTicketDTO(createTicket);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<TicketDTO> getTicket(@PathVariable Long id) {
//        Ticket ticket = findTicketUseCase.findTicket(id);
//        TicketDTO ticketDTO = ticketFactory.createTicketDTO(ticket);
//        return ResponseEntity.ok(ticketDTO);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<TicketDTO>> getAllTickets() {
//        List<Ticket> tickets = findTicketUseCase.findAllTickets();
//        List<TicketDTO> ticketDTOs = tickets.stream().map(ticketFactory::createTicketDTO)
//                .collect(Collectors.toUnmodifiableList());
//        return ResponseEntity.ok(ticketDTOs);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id,
//            @Valid @RequestBody TicketDTO ticketDTO) {
//        Ticket ticket = updateTicketUseCase.updateTicket(id, ticketFactory.createTicketDTO(ticketDTO));
//        TicketDTO updateTicketDTO = ticketFactory.createTicketDTO(ticket);
//        return ResponseEntity.status(HttpStatus.OK).body(updateTicketDTO);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<TicketDTO> deleteTicket(@PathVariable Long id) {
//        deleteTicketUseCase.deleteTicket(id);
//        return ResponseEntity.noContent().build();
//    }
}
