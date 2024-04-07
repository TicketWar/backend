package com.ticketwar.ticketwar.repository;

import com.ticketwar.ticketwar.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
