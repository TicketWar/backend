package com.ticketwar.ticketwar.repository;

import com.ticketwar.ticketwar.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
