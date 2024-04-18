package com.ticketwar.ticketwar.user.entity;

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

  @Builder
  protected User(Long id, @NonNull String nickname, @NonNull String email, String password) {
    if (id != null) {
      this.setId(id);
    }
    this.setNickname(nickname);
    this.setEmail(email);
    this.setPassword(password);
  }

  public void setId(@NonNull Long id) {
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
}
