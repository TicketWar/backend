package com.ticketwar.ticketwar.order.entity;

import com.ticketwar.ticketwar.performance.entity.Performance;
import com.ticketwar.ticketwar.ticket.entity.Ticket;
import com.ticketwar.ticketwar.user.entity.User;
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
@Table(name = "ORDERS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "performance_id", nullable = false)
  private Performance performance;

  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
  private List<Ticket> tickets = new ArrayList<>();

  @Column(name = "ordered_at", nullable = false)
  private LocalDateTime orderedAt;

  @Builder
  protected Order(
      Long id,
      @NonNull User user,
      @NonNull Performance performance,
      @NonNull LocalDateTime orderedAt) {
    setId(id);
    setUser(user);
    setPerformance(performance);
    setTickets(new ArrayList<>());
    setPerformance(performance);
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
        && Objects.equals(user, order.user) && Objects.equals(orderedAt,
        order.orderedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tickets, id, user, orderedAt);
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUser(@NonNull User user) {
    this.user = user;
  }

  public void setPerformance(@NonNull Performance performance) {
    this.performance = performance;
  }

  public void setTickets(List<Ticket> tickets) {
    if (tickets == null) {
      tickets = new ArrayList<>();
    }
    this.tickets = tickets;
  }

  public void addTicket(@NonNull Ticket ticket) {
    this.tickets.remove(ticket);
    this.tickets.add(ticket);
  }

  public void removeTicket(@NonNull Ticket ticket) {
    this.tickets.remove(ticket);
  }

  public void setOrderedAt(@NonNull LocalDateTime orderedAt) {
    this.orderedAt = orderedAt;
  }
}
