package br.com.postech.parking.ticket.application.dto;

import br.com.postech.parking.ticket.domain.TicketStatusEnum;
import br.com.postech.parking.ticket.application.gateway.jpa.entity.TicketEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketDTO (Long id,
        LocalDateTime startTime,
        LocalDateTime endTime,
        TicketStatusEnum status,
        BigDecimal price ){

    public TicketEntity toTicketEntity(){
        return TicketEntity.builder()
                .startTime(this.startTime)
                .endTime(this.endTime)
                .status(this.status)
                .price(this.price)
                .build();
    }
}
