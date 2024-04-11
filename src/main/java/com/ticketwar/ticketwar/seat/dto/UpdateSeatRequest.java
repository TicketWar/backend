package com.ticketwar.ticketwar.seat.dto;

import com.ticketwar.ticketwar.performance.entity.Performance;
import com.ticketwar.ticketwar.seat.entity.SeatStatus;
import lombok.Getter;

@Getter
public class UpdateSeatRequest {
    private Performance performance;
    private String position;
    private SeatStatus seatStatus;
}
