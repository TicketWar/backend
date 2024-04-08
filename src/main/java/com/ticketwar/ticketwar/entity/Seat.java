package com.ticketwar.ticketwar.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "SEAT")
public class Seat {

    //좌석 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    //공연 ID 1:N
    @ManyToOne
    @JoinColumn(name = "performance_id", nullable = false, updatable = false)
    private Performance performance;

    //위치 varchar(30)
    @Column(name = "position", nullable = false)
    private String position;

    //점유상태
    @Column(name = "available", nullable = false)
    private boolean available = true;

    @Builder
    public Seat(Performance performance, String position) {
        this.performance = performance;
        this.position = position;
    }
}
