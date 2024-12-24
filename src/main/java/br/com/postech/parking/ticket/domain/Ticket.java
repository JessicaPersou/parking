package br.com.postech.parking.ticket.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Ticket {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TicketStatusEnum status;
    private BigDecimal price;

    public Ticket(Long id, LocalDateTime startTime, LocalDateTime endTime, TicketStatusEnum status, BigDecimal price) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.price = price;
    }
}
