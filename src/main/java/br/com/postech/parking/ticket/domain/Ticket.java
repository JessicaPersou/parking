package br.com.postech.parking.ticket.domain;

import br.com.postech.parking.user.domain.User;
import br.com.postech.parking.vehicle.domain.Vehicle;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Ticket {

    private Long id;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private TicketStatusEnum status;
    private BigDecimal totalAmount;
    private Vehicle vehicle;
    private User user;

    public Ticket(Long id, LocalDateTime entryTime, LocalDateTime exitTime, TicketStatusEnum status, BigDecimal totalAmount) {
        this.id = id;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public boolean isExpired() {
        return exitTime.isBefore(LocalDateTime.now());
    }

    public boolean isValid(){
        return exitTime.isAfter(LocalDateTime.now());
    }

    public boolean priceIsValid(BigDecimal price) {
        return this.totalAmount.compareTo(price) >= 0 ;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
