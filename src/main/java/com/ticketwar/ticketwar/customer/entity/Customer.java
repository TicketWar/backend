package com.ticketwar.ticketwar.customer.entity;

import com.ticketwar.ticketwar.common.entity.trackable.CreatedAndUpdatedTimeTrackable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "CUSTOMER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends CreatedAndUpdatedTimeTrackable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "customer_id")
  private Long id;

  @Column(name = "nickname", length = 20, nullable = false, unique = true)
  private String nickname;

  @Column(name = "email", length = 100, nullable = false, unique = true)
  private String email;

  @Builder
  protected Customer(Long id, @NonNull String nickname, @NonNull String email) {
    if (id != null) {
      this.setId(id);
    }
    this.setNickname(nickname);
    this.setEmail(email);
  }

  public void setId(@NonNull Long id) {
    this.id = id;
  }

  public void setNickname(@NonNull String nickname) {
    this.nickname = nickname;
  }

  public void setEmail(@NonNull String email) {
    // have to verify email by regex
    this.email = email;
  }
}
