package br.com.postech.parking.vehicle.domain;

import br.com.postech.parking.owner.domain.Owner;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.vehicle.domain.valueobject.VehiclePlate;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Vehicle {

    private Long id;
    private VehiclePlate plate;
    private String model;
    private String color;
    private Owner owner;
    private List<Ticket> tickets = new ArrayList<>();

    public Vehicle(Long id, VehiclePlate plate, String model, String color) {
        this.id = id;
        this.plate = VehiclePlate.createVehiclePlateFactory(plate.getValue());
        this.model = model;
        this.color = color;
    }

    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

    public void setOwnerId(Owner owner) {
        this.owner = owner;
    }

}
