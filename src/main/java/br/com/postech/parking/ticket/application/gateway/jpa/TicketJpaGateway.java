package br.com.postech.parking.ticket.application.gateway.jpa;

import br.com.postech.parking.ticket.application.gateway.TicketGateway;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.user.domain.User;

public class TicketJpaGateway  implements TicketGateway {

    @Override
    public Ticket generateTicket() {
        return null;
    }

    @Override
    public Ticket getTicket() {
        return null;
    }

    @Override
    public Ticket allTickets() {
        return null;
    }

    @Override
    public Ticket updateTicket(Ticket ticket, User user) {
        return null;
    }

    @Override
    public Ticket deleteTicket(Ticket ticket) {
        return null;
    }
}
