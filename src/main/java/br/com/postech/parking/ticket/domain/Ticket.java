package br.com.postech.parking.ticket.domain;

import br.com.postech.parking.user.domain.User;
import br.com.postech.parking.vehicle.domain.Vehicle;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Ticket {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TicketStatusEnum status;
    private BigDecimal price;
    private Vehicle vehicle;
    private User user;

    public Ticket(Long id, LocalDateTime startTime, LocalDateTime endTime, TicketStatusEnum status, BigDecimal price) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.price = price;
    }

    public boolean isExpired() {
        return endTime.isBefore(LocalDateTime.now());
    }

    public boolean isValid(){
        return endTime.isAfter(LocalDateTime.now());
    }

    public boolean priceIsValid(BigDecimal price) {
        return this.price.compareTo(price) >= 0 ;
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
