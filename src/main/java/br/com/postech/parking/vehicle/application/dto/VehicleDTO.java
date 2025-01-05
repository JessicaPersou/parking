package br.com.postech.parking.vehicle.application.dto;

import br.com.postech.parking.ticket.application.gateway.jpa.entity.TicketEntity;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.user.application.gateway.jpa.entity.UserEntity;
import br.com.postech.parking.vehicle.application.gateway.jpa.entity.VehicleEntity;
import br.com.postech.parking.vehicle.domain.Vehicle;
import java.util.List;

public record VehicleDTO(
        Long id,
        String plate,
        String model,
        String color,
        Long userId,
        List<Long> ticketIds
) {

    public static VehicleDTO fromVehicleEntity(VehicleEntity entity) {
        return new VehicleDTO(
                entity.getId(),
                entity.getPlate(),
                entity.getModel(),
                entity.getColor(),
                entity.getUser().getId(),
                entity.getTickets().stream().map(TicketEntity::getId).toList()

        );
    }

    public static VehicleDTO fromVehicleDomain(Vehicle vehicle) {
        return new VehicleDTO(
                vehicle.getId(),
                vehicle.getPlate().getValue(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getUser().getId(),
                vehicle.getTickets().stream().map(Ticket::getId).toList()
        );
    }

    public VehicleEntity toVehicleEntity() {
        return VehicleEntity.builder()
                .id(this.id)
                .plate(this.plate)
                .model(this.model)
                .color(this.color)
                .user(this.userId != null ? UserEntity.builder().id(this.userId).build() : null)
                .build();
    }
}