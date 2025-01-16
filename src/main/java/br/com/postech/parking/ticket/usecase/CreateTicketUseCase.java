package br.com.postech.parking.ticket.usecase;

import br.com.postech.parking.exception.CalculationErrorException;
import br.com.postech.parking.exception.InvalidOperationException;
import br.com.postech.parking.owner.application.gateway.OwnerGateway;
import br.com.postech.parking.owner.domain.Owner;
import br.com.postech.parking.ticket.application.dto.TicketRequestDTO;
import br.com.postech.parking.ticket.application.gateway.TicketGateway;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.ticket.domain.TicketStatusEnum;
import br.com.postech.parking.ticket.domain.factory.TicketFactory;
import br.com.postech.parking.vehicle.application.gateway.VehicleGateway;
import br.com.postech.parking.vehicle.domain.Vehicle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateTicketUseCase {
    private final TicketGateway ticketGateway;
    private final VehicleGateway vehicleGateway;
    private final OwnerGateway ownerGateway;
    private final TicketFactory ticketFactory;

    public Ticket execute(TicketRequestDTO requestDTO) {
        Vehicle vehicle = vehicleGateway.findById(requestDTO.vehicleId());
        Owner owner = ownerGateway.findOwnerById(requestDTO.ownerId());

        if (vehicle == null || owner == null) {
            throw new InvalidOperationException("Ticket values are null");
        }

        Ticket ticket = ticketFactory.createTicket(vehicle, owner);

        log.info("Creating ticket: {}", ticket);

        if (ticket.isExpired()) {
            log.info("Ticket is expired");
            throw new InvalidOperationException("Ticket is expired");
        }

        if (!ticket.priceIsValid(ticket.getTotalAmount())) {
            log.info("Ticket price is invalid");
            throw new CalculationErrorException("Ticket price is invalid");
        }

        ticketGateway.generateTicket(ticket);
        log.info("Ticket created: {}", ticket);
        return ticket;
    }
}
