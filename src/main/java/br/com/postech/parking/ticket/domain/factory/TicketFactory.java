package br.com.postech.parking.ticket.domain.factory;

import br.com.postech.parking.comon.DateTimeFormatterUtil;
import br.com.postech.parking.owner.domain.Owner;
import br.com.postech.parking.ticket.application.dto.TicketRequestDTO;
import br.com.postech.parking.ticket.application.dto.TicketResponseDTO;
import br.com.postech.parking.ticket.application.gateway.jpa.entity.TicketEntity;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.ticket.domain.TicketPriceCalculator;
import br.com.postech.parking.ticket.domain.TicketStatusEnum;
import br.com.postech.parking.vehicle.domain.Vehicle;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static br.com.postech.parking.comon.DateTimeFormatterUtil.calculateDurationInHours;

@Component
public class TicketFactory {

    public Ticket createTicket(Vehicle vehicle, Owner owner, TicketRequestDTO request) {
        if (vehicle == null || owner == null) {
            throw new IllegalArgumentException("Vehicle and Owner cannot be null");
        }

        LocalDateTime entryTime = LocalDateTime.now();
        LocalDateTime exitTime = entryTime.plusHours(request.duration() != null ? request.duration() : 2);

        TicketPriceCalculator calculator = new TicketPriceCalculator();
        BigDecimal totalAmount = calculator.calculateTotalAmount(entryTime, exitTime);

        return new Ticket(
                null,
                entryTime,
                exitTime,
                calculateDurationInHours(entryTime, exitTime),
                TicketStatusEnum.ACTIVE,
                totalAmount,
                vehicle,
                owner
        );
    }

    public Ticket createTicketComplete(Vehicle vehicle, Owner owner, TicketEntity entity) {
        if (vehicle == null || owner == null) {
            throw new IllegalArgumentException("Vehicle and Owner cannot be null");
        }

        return new Ticket(
                entity.getId(),
                entity.getEntryTime(),
                entity.getExitTime(),
                entity.getDuration(),
                entity.getStatus(),
                entity.getTotalAmount(),
                vehicle,
                owner
        );
    }

    public TicketResponseDTO createTicketDTO(Ticket ticket) {
        return new TicketResponseDTO(
                ticket.getId(),
                ticket.getEntryTime(),
                ticket.getExitTime(),
                ticket.getDuration(),
                ticket.getStatus(),
                ticket.getTotalAmount(),
                ticket.getVehicle().getId(),
                ticket.getOwner().getId()
        );
    }
}
