package com.ticketwar.ticketwar.ticket.entity;

import com.ticketwar.ticketwar.common.entity.trackable.CreatedTimeTrackable;
import com.ticketwar.ticketwar.order.entity.Order;
import com.ticketwar.ticketwar.performance.entity.Performance;
import com.ticketwar.ticketwar.seat.entity.Seat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "TICKETS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket extends CreatedTimeTrackable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ticket_id")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "seat_id", nullable = false)
  private Seat seat;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "performance_id", nullable = false)
  private Performance performance;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @Builder
  protected Ticket(Long id,
      @NonNull Seat seat,
      @NonNull Performance performance,
      @NonNull Order order) {
    setId(id);
    setSeat(seat);
    setPerformance(performance);
    setOrder(order);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ticket ticket = (Ticket) o;
    return Objects.equals(order, ticket.order) && Objects.equals(id, ticket.id)
        && Objects.equals(seat, ticket.seat) && Objects.equals(performance,
        ticket.performance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(order, id, seat, performance);
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setSeat(@NonNull Seat seat) {
    this.seat = seat;
  }

  public void setPerformance(@NonNull Performance performance) {
    this.performance = performance;
  }

  public void setOrder(@NonNull Order order) {
    this.order = order;
    order.addTicket(this);
  }
}
