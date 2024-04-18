package com.ticketwar.ticketwar.user.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ticketwar.ticketwar.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

  @Autowired
  private UserRepository repository;
  private User user;
  private User userInvalid;

  @BeforeEach
  public void setUp() {
    user = User.builder()
               .nickname("sungjpar")
               .email("sungjpar@test.com")
               .build();
    userInvalid = User.builder()
                      .nickname("test")
                      .email("test@test.com")
                      .build();
  }

  @DisplayName("Test find by id")
  @Test
  public void test_find_by_id() {
    User saved = repository.saveAndFlush(user);
    User user = repository.findById(saved.getId()).orElse(userInvalid);

    assertEquals(user, this.user);
  }

  @DisplayName("Test find by Nickname")
  @Test
  public void test_find_by_nickname() {
    User saved = repository.saveAndFlush(user);
    User user = repository.findByNickname(saved.getNickname()).orElse(userInvalid);

    assertEquals(this.user.getEmail(), user.getEmail());
    assertEquals(this.user.getNickname(), user.getNickname());
    assertEquals(saved.getId(), user.getId());
  }

  @DisplayName("Test find by Nickname not exist case")
  @Test
  public void test_find_by_nickname_not_exist() {
    User saved = repository.saveAndFlush(user);

    assertEquals(repository.findByNickname("test").isPresent(), false);
  }
}
