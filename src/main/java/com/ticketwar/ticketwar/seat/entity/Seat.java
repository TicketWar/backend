package com.ticketwar.ticketwar.seat.entity;

import com.ticketwar.ticketwar.performance.entity.Performance;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "SEAT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "seat_id")
  private Long id;

  @ManyToOne // many seats have fk to one performance
  @JoinColumn(name = "performance_id", nullable = false)
  private Performance performance;

  @Column(name = "position", length = 30, nullable = false)
  private String position;

  @Column(name = "available", nullable = false)
  private SeatStatus seatStatus;

  @Builder
  protected Seat(
      @NonNull Performance performance, @NonNull String position, @NonNull SeatStatus seatStatus) {
    this.performance = performance;
    this.position = position;
    this.seatStatus = seatStatus;
  }
}
