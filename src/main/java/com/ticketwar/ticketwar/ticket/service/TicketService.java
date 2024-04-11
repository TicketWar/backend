package com.ticketwar.ticketwar.ticket.service;

import com.ticketwar.ticketwar.ticket.dto.AddTicketRequest;
import com.ticketwar.ticketwar.ticket.dto.UpdateTicketRequest;
import com.ticketwar.ticketwar.ticket.entity.Ticket;
import com.ticketwar.ticketwar.ticket.exception.TicketNotFoundException;
import com.ticketwar.ticketwar.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
  private final TicketRepository ticketRepository;

  public TicketService(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  // 추가
  public Ticket save(AddTicketRequest request) {
    return ticketRepository.save(request.toEntity());
  }

  // findAll
  public List<Ticket> findAll() {
    return ticketRepository.findAll();
  }

  // findById
  public Ticket findById(Long id) {
    return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("not found: " + id));
  }

  // delete
  public void delete(Long id) {
    ticketRepository.deleteById(id);
  }

  // update
  public Ticket update(Long id, UpdateTicketRequest request) {
    Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("not found: " + id));
    ticket.update(request.getSeat(), request.getPerformance());
    return ticket;
  }
}
