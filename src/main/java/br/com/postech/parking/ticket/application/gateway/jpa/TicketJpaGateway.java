package br.com.postech.parking.ticket.application.gateway.jpa;

import br.com.postech.parking.ticket.application.dto.TicketDTO;
import br.com.postech.parking.ticket.application.gateway.TicketGateway;
import br.com.postech.parking.ticket.application.gateway.jpa.entity.TicketEntity;
import br.com.postech.parking.ticket.application.gateway.jpa.repository.TicketRepository;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.ticket.domain.factory.TicketFactory;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TicketJpaGateway  implements TicketGateway {

    private final TicketRepository ticketRepository;
    private final TicketFactory ticketFactory;

    @Override
    public Ticket generateTicket(Ticket ticket) {
        TicketDTO dto = ticketFactory.createTicket(ticket);
        TicketEntity entity = dto.toTicketEntity();

        ticketRepository.save(entity);
        return convertToUserEntity(entity);
    }

    @Override
    public Optional<Ticket> getTicket(Long id) {
        return ticketRepository.findById(id).map(this::convertToUserEntity);
    }

    @Override
    public List<Ticket> allTickets() {
        List<Ticket> ticketsList = ticketRepository.findAll()
                .stream()
                .map(this::convertToUserEntity)
                .collect(Collectors.toUnmodifiableList());
        return ticketsList;
    }

    @Override
    public Ticket updateTicket(Long id, Ticket ticket) {
        TicketEntity entityExist = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id: " + id));
        entityExist.setStatus(ticket.getStatus());
        entityExist.setPrice(ticket.getPrice());
        entityExist.setStartTime(ticket.getStartTime());

        //TODO: Calcular tempo e adicionar o tempo para finalizar o tempo no estacionamento
        entityExist.setEndTime(ticket.getEndTime());
        ticketRepository.save(entityExist);
        log.info("Ticket updated with id: " + id);

        return convertToUserEntity(entityExist);
    }

    @Override
    public void deleteTicket(Long id) {
        TicketEntity entityExist = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id: " + id));
        log.info("Deleting user with id: {}", id);
        ticketRepository.delete(entityExist);
    }

    public Ticket convertToUserEntity(TicketEntity entity) {
        return new Ticket(
                entity.getId(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getStatus(),
                entity.getPrice());
    }
}
