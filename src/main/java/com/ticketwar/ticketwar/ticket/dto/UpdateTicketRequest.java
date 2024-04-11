package com.ticketwar.ticketwar.ticket.dto;

import com.ticketwar.ticketwar.performance.entity.Performance;
import com.ticketwar.ticketwar.seat.entity.Seat;
import lombok.Getter;

@Getter
public class UpdateTicketRequest {
    Seat seat;
    Performance performance;
}
