package br.com.postech.parking.ticket.application.gateway.jpa.repository;

import br.com.postech.parking.ticket.application.gateway.jpa.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

}
