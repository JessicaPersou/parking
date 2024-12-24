package br.com.postech.parking.ticket.application.gateway;

import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.user.domain.User;
import br.com.postech.parking.vehicle.domain.Vehicle;

public interface TicketGateway {

    public Ticket generateTicket();

    public Ticket getTicket();

    public Ticket allTickets();

    public Ticket updateTicket(Ticket ticket, User user);

    public Ticket deleteTicket(Ticket ticket);

}
