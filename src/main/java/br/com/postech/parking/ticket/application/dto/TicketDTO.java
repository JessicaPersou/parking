package br.com.postech.parking.ticket.application.dto;

import br.com.postech.parking.ticket.application.gateway.jpa.entity.TicketEntity;
import br.com.postech.parking.ticket.domain.TicketStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketDTO(
        Long id,
        LocalDateTime entryTime,
        LocalDateTime exitTime,
        TicketStatusEnum status,
        BigDecimal totalAmount,
        Long vehicleId,
        Long ownerId
) {
    public TicketEntity toTicketEntity() {
        return TicketEntity.builder()
                .entryTime(this.entryTime)
                .exitTime(this.exitTime)
                .status(this.status)
                .totalAmount(this.totalAmount)
                .build();
    }
}
