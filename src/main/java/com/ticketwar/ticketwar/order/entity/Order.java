package com.ticketwar.ticketwar.order.entity;

import com.ticketwar.ticketwar.customer.entity.Customer;
import com.ticketwar.ticketwar.performance.entity.Performance;
import com.ticketwar.ticketwar.ticket.entity.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private Customer customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "performance_id", nullable = false)
  private Performance performance;

  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
  List<Ticket> tickets = new ArrayList<>();
  
  @Column(name = "ordered_at", nullable = false)
  private LocalDateTime orderedAt;

  @Builder
  protected Order(@NonNull Customer customer, @NonNull Performance performance,
      @NonNull LocalDateTime orderedAt) {
    this.customer = customer;
    this.performance = performance;
    this.orderedAt = orderedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(tickets, order.tickets) && Objects.equals(id, order.id)
        && Objects.equals(customer, order.customer) && Objects.equals(orderedAt,
        order.orderedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tickets, id, customer, orderedAt);
  }
}
