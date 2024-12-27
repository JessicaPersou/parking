package br.com.postech.parking.vehicle.domain;

import br.com.postech.parking.exception.InvalidFormatException;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.user.domain.User;
import br.com.postech.parking.vehicle.domain.valueobject.VehiclePlate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Vehicle {

    private Long id;
    private VehiclePlate plate;
    private String model;
    private String color;
    private LocalDateTime inputDate;
    private LocalDateTime exitDate;
    private User owner; // Referência ao usuário dono do veículo
    private List<Ticket> tickets = new ArrayList<>();

    public Vehicle(Long id, VehiclePlate plate, String model, String color, LocalDateTime inputDate,
            LocalDateTime exitDate) {
        this.id = id;
        this.plate = VehiclePlate.createVehiclePlateFactory(plate.getValue());
        this.model = model;
        this.color = color;
        this.inputDate = inputDate;
        this.exitDate = exitDate;
    }

    public void initializeInputDateIfNull() {
        if (this.inputDate == null) {
            this.inputDate = LocalDateTime.now();
        }
    }

    public LocalDateTime validateExitDate(LocalDateTime inputDate, LocalDateTime exitDate) {
        if (exitDate == null) {
            return inputDate;
        }
        if (inputDate.isAfter(exitDate)) {
            throw new InvalidFormatException("The input date must not be after the exit date.");
        }
        return inputDate;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        this.tickets.remove(ticket);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }
}
