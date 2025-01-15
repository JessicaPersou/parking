package br.com.postech.parking.ticket.application.dto;

public record TicketRequestDTO(
        Long vehicleId,
        Long ownerId
) {
}
