package br.com.postech.parking.vehicle.domain;

import br.com.postech.parking.exception.InvalidFormatException;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.user.domain.User;
import br.com.postech.parking.vehicle.domain.valueobject.VehiclePlate;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.Getter;

@Getter
public class Vehicle {

    private Long id;
    private VehiclePlate plate;
    private String model;
    private String color;
    private User user;
    private List<Ticket> tickets = new ArrayList<>();

    public Vehicle(Long id, VehiclePlate plate, String model, String color) {
        this.id = id;
        this.plate = VehiclePlate.createVehiclePlateFactory(plate.getValue());
        this.model = model;
        this.color = color;
    }

    //deixar a validaçao no dominio de ticket
//    public void initializeInputDateIfNull() {
//        if (this.inputDate == null) {
//            this.inputDate = LocalDateTime.now();
//        }
//    }

    //deixar a validaçao no dominio de ticket
//    public LocalDateTime validateExitDate(LocalDateTime inputDate, LocalDateTime exitDate) {
//        if (exitDate == null) {
//            return null;
//        }
//        if (inputDate.isAfter(exitDate)) {
//            throw new InvalidFormatException("The input date must not be after the exit date.");
//        }
//        return inputDate;
//    }

    public void addTicket(Ticket ticket) {
        Objects.requireNonNull(ticket, "Object can't be null");
        this.tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        this.tickets.remove(ticket);
    }

    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

    public void setUserId(User user) {
        this.user = user;
    }

    public void validateUser() {
        if (this.user == null) {
            throw new EntityNotFoundException("Vehicle must have an owner");
        }
    }
}
