package com.ticketwar.ticketwar.ticket.entity;

import com.ticketwar.ticketwar.common.entity.trackable.CreatedTimeTrackable;
import com.ticketwar.ticketwar.order.entity.Order;
import com.ticketwar.ticketwar.performance.entity.Performance;
import com.ticketwar.ticketwar.seat.entity.Seat;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity(name = "TICKET")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket extends CreatedTimeTrackable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ticket_id")
  private Long id;

  @OneToOne
  @JoinColumn(name = "seat_id", nullable = false)
  private @NonNull Seat seat;

  @ManyToOne
  @JoinColumn(name = "performance_id", nullable = false)
  private @NonNull Performance performance;

  @ManyToMany
  @JoinTable(
      name = "ORDER_TICKET",
      joinColumns = @JoinColumn(name = "ticket_id"),
      inverseJoinColumns = @JoinColumn(name = "order_id"))
  private List<Order> orders = new ArrayList<>();

  @Builder
  protected Ticket(@NonNull Seat seat, @NonNull Performance performance) {
    this.seat = seat;
    this.performance = performance;
  }
}
