package com.ticketwar.ticketwar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@EqualsAndHashCode @ToString
@RequiredArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SEAT")
public class Seat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    @ManyToOne // many seats have fk to one performance
    @JoinColumn(name="performance_id", nullable=false)
    private @NonNull Performance performance;

    @Column(name = "position", columnDefinition = "varchar(30)")
    private @NonNull String position;

    @Column(name = "available")
    private boolean available = true;
}
