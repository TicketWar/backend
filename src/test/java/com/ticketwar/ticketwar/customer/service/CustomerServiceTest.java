package com.ticketwar.ticketwar.customer.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ticketwar.ticketwar.customer.dto.CustomerReqDto;
import com.ticketwar.ticketwar.customer.entity.Customer;
import com.ticketwar.ticketwar.customer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Transactional
class CustomerServiceTest {

  @Autowired
  private CustomerService service;
  @Autowired
  private CustomerRepository repository;

  @Nested
  @DisplayName("회원가입 - signUp")
  public class Describe_Sign_Up {

    @DisplayName("정상 - 일반")
    @Test
    public void test_sign_up_valid_case() {
      // given
      String nickname = "sungjpar";
      String email = "sungjpar@student.42seoul.kr";
      CustomerReqDto dto = CustomerReqDto.builder()
                                         .nickname(nickname)
                                         .email(email)
                                         .build();
      // when
      assertDoesNotThrow(() -> service.signUp(dto));
      Optional<Customer> saved = repository.findByNickname(nickname);

      // then
      assertEquals(true, saved.isPresent());
      assertEquals(nickname, saved.get().getNickname());
      assertEquals(email, saved.get().getEmail());
    }

    @DisplayName("실패 - 닉네임 중복")
    @Test
    public void test_sign_up_duplicate_nickname() {
      // given
      String nickname = "sungjpar";
      String email = "sungjpar@student.42seoul.kr";
      Customer c = Customer.builder()
                           .nickname(nickname)
                           .email(email)
                           .build();
      CustomerReqDto dto = CustomerReqDto.builder()
                                         .nickname(nickname)
                                         .email("another@test.com")
                                         .build();
      repository.saveAndFlush(c);

      // when
      assertThrows(BadRequestException.class, () -> service.signUp(dto));
      Optional<Customer> saved = repository.findByNickname(nickname);

      // then
      assertEquals(true, saved.isPresent());
      assertEquals(nickname, saved.get().getNickname());
      assertEquals(email, saved.get().getEmail());
    }

    @DisplayName("실패 - 이메일 중복")
    @Test
    public void test_sign_up_duplicate_email() {

      // given
      String nickname = "sungjpar";
      String email = "sungjpar@student.42seoul.kr";
      Customer c = Customer.builder()
                           .nickname(nickname)
                           .email(email)
                           .build();
      CustomerReqDto dto = CustomerReqDto.builder()
                                         .nickname("test")
                                         .email(email)
                                         .build();
      repository.saveAndFlush(c);

      // when
      assertThrows(BadRequestException.class, () -> service.signUp(dto));
      Optional<Customer> saved = repository.findByEmail(email);

      // then
      assertEquals(true, saved.isPresent());
      assertEquals(nickname, saved.get().getNickname());
      assertEquals(email, saved.get().getEmail());
    }

  }

  @Nested
  @DisplayName("업데이트 - update")
  public class Describe_update {

    @Test
    @DisplayName("정상 - 일반")
    public void test_update() {
      // given
      String nickname = "sungjpar";
      String updatedNickname = "test";
      String email = "sungjpar@student.42seoul.kr";
      Customer c = Customer.builder()
                           .nickname(nickname)
                           .email(email)
                           .build();
      CustomerReqDto dto = CustomerReqDto.builder()
                                         .nickname(updatedNickname)
                                         .email(email)
                                         .build();
      repository.saveAndFlush(c);

      // when
      assertDoesNotThrow(() -> service.update(c.getId(), dto));
      // then
      Optional<Customer> saved = repository.findById(c.getId());
      // then
      assertEquals(true, saved.isPresent());
      assertEquals(updatedNickname, saved.get().getNickname());
      assertEquals(email, saved.get().getEmail());
    }

    @Test
    @DisplayName("정상 - 동일 정보")
    public void test_same_data_update() {
      // given
      String nickname = "sungjpar";
      String email = "sungjpar@student.42seoul.kr";
      Customer c = Customer.builder()
                           .nickname(nickname)
                           .email(email)
                           .build();
      CustomerReqDto dto = CustomerReqDto.builder()
                                         .nickname(nickname)
                                         .email(email)
                                         .build();
      repository.saveAndFlush(c);

      // when
      assertDoesNotThrow(() -> service.update(c.getId(), dto));
      // then
      Optional<Customer> saved = repository.findById(c.getId());
      // then
      assertEquals(true, saved.isPresent());
      assertEquals(nickname, saved.get().getNickname());
      assertEquals(email, saved.get().getEmail());
    }


    @Test
    @DisplayName("실패 - 닉네임 중복")
    public void test_duplicate_nickname() {
      // given
      String nickname_1 = "sungjpar";
      String nickname_2 = "minkyeki";
      String email_1 = "sungjpar@student.42seoul.kr";
      String email_2 = "minkyeki@student.42seoul.kr";
      Customer c1 = Customer.builder()
                            .nickname(nickname_1)
                            .email(email_1)
                            .build();
      Customer c2 = Customer.builder()
                            .nickname(nickname_2)
                            .email(email_2)
                            .build();
      CustomerReqDto dto = CustomerReqDto.builder()
                                         .nickname(nickname_1)
                                         .email(email_2)
                                         .build();
      repository.saveAndFlush(c1);
      repository.saveAndFlush(c2);

      // when
      assertThrows(BadRequestException.class, () -> service.update(c2.getId(), dto));
      // then
      Optional<Customer> saved = repository.findById(c2.getId());
      // then
      assertEquals(true, saved.isPresent());
      assertEquals(nickname_2, saved.get().getNickname());
      assertEquals(email_2, saved.get().getEmail());
    }

    @Test
    @DisplayName("실패 - 이메일 중복")
    public void test_duplicate_email() {
      // given
      String nickname_1 = "sungjpar";
      String nickname_2 = "minkyeki";
      String email_1 = "sungjpar@student.42seoul.kr";
      String email_2 = "minkyeki@student.42seoul.kr";
      Customer c1 = Customer.builder()
                            .nickname(nickname_1)
                            .email(email_1)
                            .build();
      Customer c2 = Customer.builder()
                            .nickname(nickname_2)
                            .email(email_2)
                            .build();
      CustomerReqDto dto = CustomerReqDto.builder()
                                         .nickname(nickname_1)
                                         .email(email_2)
                                         .build();
      repository.saveAndFlush(c1);
      repository.saveAndFlush(c2);

      // when
      assertThrows(BadRequestException.class, () -> service.update(c2.getId(), dto));
      // then
      Optional<Customer> saved = repository.findById(c2.getId());
      // then
      assertEquals(true, saved.isPresent());
      assertEquals(nickname_2, saved.get().getNickname());
      assertEquals(email_2, saved.get().getEmail());
    }

  }

}
