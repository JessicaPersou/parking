package br.com.postech.parking.vehicle.application.dto;

import br.com.postech.parking.owner.application.gateway.jpa.entity.OwnerEntity;
import br.com.postech.parking.vehicle.application.gateway.jpa.entity.VehicleEntity;

import java.util.List;

public record VehicleDTO(
        Long id,
        String plate,
        String model,
        String color,
        Long ownerId,
        List<Long> ticketIds
) {

    public VehicleEntity toVehicleEntity() {
        return VehicleEntity.builder()
                .id(this.id)
                .plate(this.plate)
                .model(this.model)
                .color(this.color)
                .owner(this.ownerId != null ? OwnerEntity.builder().id(this.ownerId).build() : null)
                .build();
    }
}