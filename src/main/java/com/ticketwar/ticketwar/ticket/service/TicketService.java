package com.ticketwar.ticketwar.ticket.service;

import com.ticketwar.ticketwar.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
  private final TicketRepository ticketRepository;

  public TicketService(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }
}
