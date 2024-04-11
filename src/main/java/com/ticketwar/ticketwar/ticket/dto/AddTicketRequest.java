package com.ticketwar.ticketwar.ticket.dto;

import com.ticketwar.ticketwar.performance.entity.Performance;
import com.ticketwar.ticketwar.seat.entity.Seat;
import com.ticketwar.ticketwar.ticket.entity.Ticket;

public class AddTicketRequest {
    Seat seat;
    Performance performance;

    public Ticket toEntity() {
        return Ticket.builder()
                .seat(seat)
                .performance(performance)
                .build();
    }
}
