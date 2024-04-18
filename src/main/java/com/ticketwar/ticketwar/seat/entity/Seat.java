package com.ticketwar.ticketwar.seat.entity;

import com.ticketwar.ticketwar.performance.entity.Performance;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "SEATS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "seat_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY) // many seats have fk to one performance
  @JoinColumn(name = "performance_id", nullable = false)
  private Performance performance;

  @Column(name = "position", length = 30, nullable = false)
  private String position;

  @Column(name = "available", nullable = false)
  private SeatStatus seatStatus;

  @Builder
  protected Seat(
      Long id,
      @NonNull Performance performance,
      @NonNull String position,
      @NonNull SeatStatus seatStatus) {
    if (id != null) {
      setId(id);
    }
    setPerformance(performance);
    setPosition(position);
    setSeatStatus(seatStatus);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Seat seat = (Seat) o;
    return Objects.equals(id, seat.id) && Objects.equals(performance,
        seat.performance) && Objects.equals(position, seat.position)
        && seatStatus == seat.seatStatus;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, performance, position, seatStatus);
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setPerformance(Performance performance) {
    this.performance = performance;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public void setSeatStatus(SeatStatus seatStatus) {
    this.seatStatus = seatStatus;
  }
}
