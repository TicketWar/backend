package com.ticketwar.ticketwar.ticket.entity;

import com.ticketwar.ticketwar.common.entity.trackable.CreatedTimeTrackable;
import com.ticketwar.ticketwar.order.entity.Order;
import com.ticketwar.ticketwar.performance.entity.Performance;
import com.ticketwar.ticketwar.seat.entity.Seat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "TICKET")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket extends CreatedTimeTrackable {

  @ManyToMany
  @JoinTable(
      name = "ORDER_TICKET",
      joinColumns = @JoinColumn(name = "ticket_id"),
      inverseJoinColumns = @JoinColumn(name = "order_id"))
  private final List<Order> orders = new ArrayList<>();
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ticket_id")
  private Long id;
  @OneToOne
  @JoinColumn(name = "seat_id", nullable = false)
  private Seat seat;
  @ManyToOne
  @JoinColumn(name = "performance_id", nullable = false)
  private Performance performance;

  @Builder
  protected Ticket(@NonNull Seat seat, @NonNull Performance performance) {
    this.seat = seat;
    this.performance = performance;
  }
}
