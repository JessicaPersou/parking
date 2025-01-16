package br.com.postech.parking.ticket.domain.factory;

import br.com.postech.parking.owner.domain.Owner;
import br.com.postech.parking.ticket.application.dto.TicketResponseDTO;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.ticket.domain.TicketStatusEnum;
import br.com.postech.parking.vehicle.domain.Vehicle;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class TicketFactory {

    public Ticket createTicket(Vehicle vehicle, Owner owner) {
        if (vehicle == null || owner == null) {
            throw new IllegalArgumentException("Vehicle and Owner cannot be null");
        }

        return new Ticket(
                null,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2),
                TicketStatusEnum.ACTIVE,
                BigDecimal.ZERO,
                vehicle,
                owner
        );
    }

    public TicketResponseDTO createTicketDTO(Ticket ticket) {
        return new TicketResponseDTO(
                ticket.getId(),
                ticket.getEntryTime(),
                ticket.getExitTime(),
                ticket.getStatus(),
                ticket.getTotalAmount(),
                ticket.getVehicle().getId(),
                ticket.getOwner().getId()
        );
    }
}
