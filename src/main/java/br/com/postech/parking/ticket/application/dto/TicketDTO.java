package br.com.postech.parking.ticket.application.dto;

import br.com.postech.parking.ticket.domain.TicketStatusEnum;
import br.com.postech.parking.ticket.application.gateway.jpa.entity.TicketEntity;
import br.com.postech.parking.user.application.gateway.jpa.entity.UserEntity;
import br.com.postech.parking.vehicle.application.gateway.jpa.entity.VehicleEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketDTO (
        Long id,
        LocalDateTime startTime,
        LocalDateTime endTime,
        TicketStatusEnum status,
        BigDecimal price,
        Long vehicleId,
        Long userId
){

    public TicketEntity toTicketEntity(){
        return TicketEntity.builder()
                .startTime(this.startTime)
                .endTime(this.endTime)
                .status(this.status)
                .price(this.price)
                .build();
    }


    public static TicketDTO fromTicketEntity(TicketEntity entity) {
        return new TicketDTO(
                entity.getId(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getStatus(),
                entity.getPrice(),
                entity.getVehicle() != null ? entity.getVehicle().getId() : null,
                entity.getUser() != null ? entity.getUser().getId() : null
        );
    }
}
