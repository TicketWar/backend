package com.ticketwar.ticketwar.ticket.repository;

import com.ticketwar.ticketwar.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
