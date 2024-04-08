package com.ticketwar.ticketwar.order.entity;

import com.ticketwar.ticketwar.customer.entity.Customer;
import com.ticketwar.ticketwar.ticket.entity.Ticket;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity(name = "ORDER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private @NonNull Customer customer;

  @Column(name = "ordered_at", nullable = false)
  private @NonNull LocalDateTime orderedAt;

  @ManyToMany(mappedBy = "orders") // in Ticket.java
  List<Ticket> tickets = new ArrayList<>();

  @Builder
  protected Order(@NonNull Customer customer, @NonNull LocalDateTime orderedAt) {
    this.customer = customer;
    this.orderedAt = orderedAt;
  }
}
