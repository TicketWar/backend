package com.ticketwar.ticketwar.customer.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ticketwar.ticketwar.customer.dto.CustomerReqDto;
import com.ticketwar.ticketwar.customer.dto.CustomerResDto;
import com.ticketwar.ticketwar.customer.entity.Customer;
import com.ticketwar.ticketwar.customer.repository.CustomerRepository;
import com.ticketwar.ticketwar.customer.utils.CustomerMapper;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;


@SpringBootTest
@Transactional
class CustomerServiceTest {

  @Autowired
  private CustomerService service;
  @Autowired
  private CustomerRepository repository;
  private final CustomerMapper mapper = new CustomerMapper();

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
      assertTrue(saved.isPresent());
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
      assertTrue(saved.isPresent());
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
      assertTrue(saved.isPresent());
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
      assertTrue(saved.isPresent());
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
      assertTrue(saved.isPresent());
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
      assertTrue(saved.isPresent());
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
      assertTrue(saved.isPresent());
      assertEquals(nickname_2, saved.get().getNickname());
      assertEquals(email_2, saved.get().getEmail());
    }

  }

  @Nested
  @DisplayName("회원 정보 획득 - getById")
  public class TestGetCustomerById {

    private Customer customer;

    @BeforeEach
    public void setUp() {
      customer = Customer.builder()
                         .nickname("test")
                         .email("test@test.com")
                         .build();
    }

    @Test
    @DisplayName("정상 - 있는 정보 요청")
    public void test_normal_case() {
      // given
      Customer c = repository.saveAndFlush(customer);
      CustomerResDto dto;
      CustomerResDto expected = mapper.mapToResDto(c);

      // when
      try { // 더 나은 방향이 필요함.
        dto = service.getById(c.getId());
      } catch (Exception e) {
        assertEquals(1, 0);
        return;
      }
      // then
      assertEquals(expected, dto);
    }

    @Test
    @DisplayName("실패 - 없는 정보 요청")
    public void test_not_exist_case() {
      // when
      assertThrows(NotFoundException.class, () -> service.getById(999L));
    }
  }

  @Nested
  @DisplayName("회원 정보 획득 - getByNickname")
  public class TestGetCustomerByNickname {

    private Customer customer;

    @BeforeEach
    public void setUp() {
      customer = Customer.builder()
                         .nickname("test")
                         .email("test@test.com")
                         .build();
    }

    @Test
    @DisplayName("정상 - 있는 정보 요청")
    public void test_normal_case() {
      // given
      Customer c = repository.saveAndFlush(customer);
      CustomerResDto dto;
      CustomerResDto expected = mapper.mapToResDto(c);
      // when
      try { // 더 나은 방향이 필요함.
        dto = service.getByNickname(c.getNickname());
      } catch (Exception e) {
        assertEquals(1, 0);
        return;
      }
      // then
      assertEquals(expected, dto);
    }

    @Test
    @DisplayName("실패 - 없는 정보 요청")
    public void test_not_exist_case() {
      // when
      assertThrows(NotFoundException.class, () -> service.getByNickname("tttt"));
    }
  }

  @Nested
  @DisplayName("회원 정보 획득 - getByEmail")
  public class TestGetCustomerByEmail {

    private Customer customer;

    @BeforeEach
    public void setUp() {
      customer = Customer.builder()
                         .nickname("test")
                         .email("test@test.com")
                         .build();
    }

    @Test
    @DisplayName("정상 - 있는 정보 요청")
    public void test_normal_case() {
      // given
      Customer c = repository.saveAndFlush(customer);
      CustomerResDto dto;
      CustomerResDto expected = mapper.mapToResDto(c);
      // when
      try { // 더 나은 방향이 필요함.
        dto = service.getByEmail(c.getEmail());
      } catch (Exception e) {
        assertEquals(1, 0);
        return;
      }
      // then
      assertEquals(expected, dto);
    }

    @Test
    @DisplayName("실패 - 없는 정보 요청")
    public void test_not_exist_case() {
      // when
      assertThrows(NotFoundException.class, () -> service.getByEmail("tttt"));
    }
  }
}
