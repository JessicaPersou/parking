package br.com.postech.parking.ticket.application.gateway;

import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.user.domain.User;
import br.com.postech.parking.vehicle.domain.Vehicle;
import java.util.List;
import java.util.Optional;

public interface TicketGateway {

    public Ticket generateTicket(Ticket ticket);

    public Optional<Ticket> getTicket(Long id);

    public List<Ticket> allTickets();

    public Ticket updateTicket(Long id, Ticket ticket);

    public void deleteTicket(Long id);

}
