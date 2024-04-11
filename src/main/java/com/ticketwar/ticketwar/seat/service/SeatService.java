package com.ticketwar.ticketwar.seat.service;

import com.ticketwar.ticketwar.seat.dto.AddSeatRequest;
import com.ticketwar.ticketwar.seat.dto.UpdateSeatRequest;
import com.ticketwar.ticketwar.seat.entity.Seat;
import com.ticketwar.ticketwar.seat.exception.SeatNotFoundException;
import com.ticketwar.ticketwar.seat.repository.SeatRepository;

import java.util.List;

public class SeatService {
  SeatRepository seatRepository;

  public SeatService(SeatRepository seatRepository) {
    this.seatRepository = seatRepository;
  }

  // 추가
  public Seat save(AddSeatRequest request) {
    return seatRepository.save(request.toEntity());
  }

  // findAll
  public List<Seat> findAll() {
    return seatRepository.findAll();
  }

  // findById
  public Seat findById(Long id) {
    return seatRepository.findById(id).orElseThrow(() -> new SeatNotFoundException("not found: " + id));
  }

  // deleteById
  public void delete(Long id) {
    seatRepository.deleteById(id);
  }

  // update
  public Seat update(Long id, UpdateSeatRequest request) {
    Seat seat = seatRepository.findById(id).orElseThrow(() -> new SeatNotFoundException("not found: " + id));
    seat.update(request.getPerformance(), request.getPosition(), request.getSeatStatus());
    return seat;
  }
}
