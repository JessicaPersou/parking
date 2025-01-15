package br.com.postech.parking.ticket.usecase;

import br.com.postech.parking.exception.CalculationErrorException;
import br.com.postech.parking.exception.EntityNotFoundException;
import br.com.postech.parking.exception.InvalidOperationException;
import br.com.postech.parking.owner.application.gateway.OwnerGateway;
import br.com.postech.parking.owner.domain.Owner;
import br.com.postech.parking.ticket.application.dto.TicketRequestDTO;
import br.com.postech.parking.ticket.application.gateway.TicketGateway;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.ticket.domain.TicketStatusEnum;
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

    public Ticket execute(TicketRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new InvalidOperationException("Ticket values are null");
        }

        Vehicle vehicle = vehicleGateway.findById(requestDTO.vehicleId());

        Owner owner = ownerGateway.findOwnerById(requestDTO.ownerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found"));

        Ticket ticket = new Ticket(
                null,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2),
                TicketStatusEnum.ACTIVE,
                BigDecimal.ZERO
        );

        ticket.setOwner(owner);
        ticket.setVehicle(vehicle);

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
