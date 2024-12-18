package br.com.postech.parking.domain;

import br.com.postech.parking.domain.valueobject.VehiclePlate;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Vehicle {

    private Long id;
    private VehiclePlate plate;
    private String model;
    private String color;
    private LocalDateTime inputDate;
    private LocalDateTime exitDate;

    public Vehicle(Long id, VehiclePlate plate, String model, String color, LocalDateTime inputDate,
            LocalDateTime exitDate) {
        this.id = id;
        this.plate = VehiclePlate.createVehiclePlateFactory(plate.getValue());
        this.model = model;
        this.color = color;
        this.inputDate = inputDate;
        this.exitDate = exitDate;
    }


}
