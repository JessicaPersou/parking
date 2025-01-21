package br.com.postech.parking.ticket.application.dto;


import br.com.postech.parking.ticket.domain.TicketStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketResponseDTO(
        Long id,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime entryTime,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime exitTime,
        Integer duration,
        TicketStatusEnum status,
        BigDecimal totalAmount,
        Long vehicleId,
        Long ownerId
) {
}
