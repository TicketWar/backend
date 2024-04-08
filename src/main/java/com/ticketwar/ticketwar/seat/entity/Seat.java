package com.ticketwar.ticketwar.seat.entity;

import com.ticketwar.ticketwar.performance.entity.Performance;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "SEAT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "seat_id")
  private Long id;

  @ManyToOne // many seats have fk to one performance
  @JoinColumn(name = "performance_id", nullable = false)
  private @NonNull Performance performance;

  @Column(name = "position", length = 30, nullable = false)
  private @NonNull String position;

  @Column(name = "available", nullable = false)
  private @NonNull SeatStatus seatStatus;

  @Builder
  protected Seat(
      @NonNull Performance performance, @NonNull String position, @NonNull SeatStatus seatStatus) {
    this.performance = performance;
    this.position = position;
    this.seatStatus = seatStatus;
  }
}
