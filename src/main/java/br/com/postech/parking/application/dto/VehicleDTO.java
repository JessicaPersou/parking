package br.com.postech.parking.application.dto;

import br.com.postech.parking.application.gateway.jpa.entity.VehicleEntity;
import java.time.LocalDateTime;

public record VehicleDTO (
         Long id,
         String plate,
         String model,
         String color,
         LocalDateTime inputDate,
         LocalDateTime exitDate
){
    public static VehicleDTO fromEntity(VehicleEntity entity) {
        return new VehicleDTO(
                entity.getId(),
                entity.getPlate(),
                entity.getModel(),
                entity.getColor(),
                entity.getInputDate(),
                entity.getExitDate()
        );
    }

    public VehicleEntity toEntity() {
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
