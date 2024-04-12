package com.ticketwar.ticketwar.order.entity;

import com.ticketwar.ticketwar.customer.entity.Customer;
import com.ticketwar.ticketwar.ticket.entity.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "CUSTOMER_ORDER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

  @ManyToMany(mappedBy = "orders") // in Ticket.java
  List<Ticket> tickets = new ArrayList<>();
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_id")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private Customer customer;
  @Column(name = "ordered_at", nullable = false)
  private LocalDateTime orderedAt;

  @Builder
  protected Order(@NonNull Customer customer, @NonNull LocalDateTime orderedAt) {
    this.customer = customer;
    this.orderedAt = orderedAt;
  }
}
