package com.ticketwar.ticketwar.user.entity;

import com.ticketwar.ticketwar.common.entity.trackable.CreatedAndUpdatedTimeTrackable;
import com.ticketwar.ticketwar.order.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends CreatedAndUpdatedTimeTrackable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private Long id;

  @Column(name = "nickname", length = 20, nullable = false, unique = true)
  private String nickname;

  @Column(name = "email", length = 100, nullable = false, unique = true)
  private String email;

  @Column(name = "password", length = 20, nullable = false)
  private String password; // have to be encrypted

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Order> orders;

  @Builder
  protected User(
      Long id,
      @NonNull String nickname,
      @NonNull String email,
      @NonNull String password,
      List<Order> orders) {
    setId(id);
    setNickname(nickname);
    setEmail(email);
    setPassword(password);
    setOrders(orders);
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNickname(@NonNull String nickname) {
    this.nickname = nickname;
  }

  public void setEmail(@NonNull String email) {
    this.email = email;
  }

  public void setPassword(@NonNull String password) {
    this.password = password;
  }

  public void setOrders(List<Order> orders) {
    if (orders == null) {
      orders = new ArrayList<>();
    }
    this.orders = orders;
  }

  public void addOrder(@NonNull Order order) {
    this.orders.remove(order);
    this.orders.add(order);
  }

  public void removeOrder(@NonNull Order order) {
    this.removeOrder(order);
  }
}
