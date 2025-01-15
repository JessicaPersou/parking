package br.com.postech.parking.ticket.application.gateway;

import br.com.postech.parking.ticket.domain.Ticket;

public interface TicketGateway {

    public Ticket generateTicket(Ticket ticket);

}
