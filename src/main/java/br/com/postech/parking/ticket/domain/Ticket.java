package br.com.postech.parking.ticket.domain;

import br.com.postech.parking.owner.domain.Owner;
import br.com.postech.parking.vehicle.domain.Vehicle;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class Ticket {

    private Long id;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Integer duration;
    private TicketStatusEnum status;
    private BigDecimal totalAmount;
    private Vehicle vehicle;
    private Owner owner;

    public Ticket() {
    }

    public Ticket(Long id, LocalDateTime entryTime, LocalDateTime exitTime, Integer duration, TicketStatusEnum status,
                  BigDecimal totalAmount, Vehicle vehicle, Owner owner) {
        this.id = id;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.duration = duration;
        this.status = status;
        this.totalAmount = totalAmount;
        this.vehicle = vehicle;
        this.owner = owner;
    }

    public boolean isExpired() {
        return exitTime.isBefore(LocalDateTime.now());
    }

    public boolean priceIsValid(BigDecimal price) {
        return this.totalAmount.compareTo(price) >= 0;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
