package com.ticketwar.ticketwar.seat.dto;

import com.ticketwar.ticketwar.performance.entity.Performance;
import com.ticketwar.ticketwar.seat.entity.Seat;
import com.ticketwar.ticketwar.seat.entity.SeatStatus;
import jakarta.persistence.Column;
import lombok.NonNull;

public class AddSeatRequest {
    private Performance performance;

    private String position;

    private SeatStatus seatStatus;

    public Seat toEntity() {
        return Seat.builder()
                .performance(performance)
                .position(position)
                .seatStatus(seatStatus)
                .build();
    }
}
