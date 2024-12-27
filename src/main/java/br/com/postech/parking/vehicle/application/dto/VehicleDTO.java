package br.com.postech.parking.vehicle.application.dto;

import br.com.postech.parking.ticket.application.gateway.jpa.entity.TicketEntity;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.user.application.gateway.jpa.entity.UserEntity;
import br.com.postech.parking.vehicle.application.gateway.jpa.entity.VehicleEntity;
import br.com.postech.parking.vehicle.domain.Vehicle;
import java.time.LocalDateTime;
import java.util.List;

public record VehicleDTO(
        Long id,
        String plate,
        String model,
        String color,
        LocalDateTime inputDate,
        LocalDateTime exitDate,
        Long userId,
        List<Long> ticketIds
) {

    public static VehicleDTO fromVehicleEntity(VehicleEntity entity) {
        return new VehicleDTO(
                entity.getId(),
                entity.getPlate(),
                entity.getModel(),
                entity.getColor(),
                entity.getInputDate(),
                entity.getExitDate(),
                entity.getUser() != null ? entity.getUser().getId() : null,
                entity.getTickets().stream().map(TicketEntity::getId).toList()

        );
    }

    public static VehicleDTO fromVehicleDomain(Vehicle vehicle) {
        return new VehicleDTO(
                vehicle.getId(),
                vehicle.getPlate().getValue(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getInputDate(),
                vehicle.getExitDate(),
                vehicle.getId(),
                vehicle.getTickets().stream().map(Ticket::getId).toList()
        );
    }

    public VehicleEntity toVehicleEntity() {
        return VehicleEntity.builder()
                .id(this.id)
                .plate(this.plate)
                .model(this.model)
                .color(this.color)
                .inputDate(this.inputDate)
                .exitDate(this.exitDate)
                .build();
    }
}