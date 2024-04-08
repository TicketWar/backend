package com.ticketwar.ticketwar.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "TICKET")
public class Ticket {
    //티켓 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false , updatable = false)
    private Long id;

    //좌석 id
    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    //공연 id
    @ManyToOne
    @JoinColumn(name = "performance_id", nullable = false)
    private Performance performance;

    //생성일자(예매일자?)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Builder
    public Ticket(Seat seat, Performance performance) {
        this.seat = seat;
        this.performance = performance;
    }
}
