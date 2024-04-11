package com.ticketwar.ticketwar.customer.entity;

import com.ticketwar.ticketwar.common.entity.trackable.CreatedAndUpdatedTimeTrackable;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "CUSTOMER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends CreatedAndUpdatedTimeTrackable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "customer_id")
  private Long id;

  @Column(name = "nickname", length = 20, nullable = false, unique = true)
  private @NonNull String nickname;

  @Column(name = "email", length = 100, nullable = false, unique = true)
  private @NonNull String email;

  @Builder
  protected Customer(@NonNull String nickname, @NonNull String email) {
    this.nickname = nickname;
    this.email = email;
  }

  public void update(String nickname, String email) {
    this.nickname = nickname;
    this.email = email;
  }
}
