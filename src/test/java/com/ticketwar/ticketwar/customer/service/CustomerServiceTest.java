package com.ticketwar.ticketwar.customer.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Transactional
class CustomerServiceTest {

  @InjectMocks
  private CustomerService service;
  @Mock
  private CustomerRepository repository;
  @Mock
  private CustomerQueryService queryService;
  @Mock
  private CustomerMapper mapper;

  @Nested
  @DisplayName("회원가입 - signUp")
  public class Describe_Sign_Up {

    @DisplayName("정상 - 일반")
    @Test
    public void test_sign_up_valid_case() {
      // given
      String nickname = "sungjpar";
      String email = "sungjpar@student.42seoul.kr";
      Long id = 1L;
      CustomerReqDto request = CustomerReqDto.builder()
                                             .nickname(nickname)
                                             .email(email)
                                             .build();
      Customer expect = Customer.builder()
                                .id(1L)
                                .nickname(nickname)
                                .email(email)
                                .build();
      BDDMockito.given(queryService.isDuplicateNickname(any(String.class))).willReturn(false);
      BDDMockito.given(queryService.isDuplicateEmail(any(String.class))).willReturn(false);
      BDDMockito.given(repository.save(any(Customer.class))).willReturn(expect);
      // when
      Customer newCustomer = assertDoesNotThrow(() -> service.signUp(request));
      assertEquals(expect, newCustomer);
    }

    @DisplayName("실패 - 닉네임 중복")
    @Test
    public void test_sign_up_duplicate_nickname() {
      // given
      CustomerReqDto request = CustomerReqDto.builder()
                                             .nickname("sungjpar")
                                             .email("email@email.com")
                                             .build();
      BDDMockito.given(queryService.isDuplicateNickname(any(String.class))).willReturn(true);
      // when, then
      assertThrows(BadRequestException.class, () -> service.signUp(request));
    }

    @DisplayName("실패 - 이메일 중복")
    @Test
    public void test_sign_up_duplicate_email() {      // given
      CustomerReqDto request = CustomerReqDto.builder()
                                             .nickname("sungjpar")
                                             .email("email@email.com")
                                             .build();
      BDDMockito.given(queryService.isDuplicateNickname(any(String.class))).willReturn(false);
      BDDMockito.given(queryService.isDuplicateEmail(any(String.class))).willReturn(true);
      // when, then
      assertThrows(BadRequestException.class, () -> service.signUp(request));
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
      String email = "sungjpar@student.42seoul.kr";
      Long id = 1L;
      CustomerReqDto request = CustomerReqDto.builder()
                                             .nickname(nickname)
                                             .email(email)
                                             .build();
      Customer expect = Customer.builder()
                                .id(1L)
                                .nickname(nickname)
                                .email(email)
                                .build();
      BDDMockito.given(repository.findById(any(Long.class))).willReturn(Optional.of(expect));
      BDDMockito.given(queryService.isDuplicateNickname(any(Long.class), any(String.class)))
                .willReturn(false);
      BDDMockito.given(queryService.isDuplicateEmail(any(Long.class), any(String.class)))
                .willReturn(false);
      BDDMockito.given(repository.save(any(Customer.class))).willReturn(expect);
      // when
      Customer updatedCustomer = assertDoesNotThrow(() -> service.update(1L, request));
      // then
      assertEquals(expect, updatedCustomer);
    }

    @Test
    @DisplayName("실패 - 닉네임 중복")
    public void test_duplicate_nickname() {
      // given
      String nickname = "sungjpar";
      String email = "sungjpar@student.42seoul.kr";
      Long id = 1L;
      CustomerReqDto request = CustomerReqDto.builder()
                                             .nickname(nickname)
                                             .email(email)
                                             .build();
      Customer expect = Customer.builder()
                                .id(1L)
                                .nickname(nickname)
                                .email(email)
                                .build();
      BDDMockito.given(repository.findById(any(Long.class))).willReturn(Optional.of(expect));
      BDDMockito.given(queryService.isDuplicateNickname(any(Long.class), any(String.class)))
                .willReturn(true);
      BDDMockito.given(queryService.isDuplicateEmail(any(Long.class), any(String.class)))
                .willReturn(false);
      BDDMockito.given(repository.save(any(Customer.class))).willReturn(expect);
      // when then
      assertThrows(BadRequestException.class, () -> service.update(1L, request));
    }

    @Test
    @DisplayName("실패 - 이메일 중복")
    public void test_duplicate_email() {
      // given
      String nickname = "sungjpar";
      String email = "sungjpar@student.42seoul.kr";
      Long id = 1L;
      CustomerReqDto request = CustomerReqDto.builder()
                                             .nickname(nickname)
                                             .email(email)
                                             .build();
      Customer expect = Customer.builder()
                                .id(1L)
                                .nickname(nickname)
                                .email(email)
                                .build();
      BDDMockito.given(repository.findById(any(Long.class))).willReturn(Optional.of(expect));
      BDDMockito.given(queryService.isDuplicateNickname(any(Long.class), any(String.class)))
                .willReturn(false);
      BDDMockito.given(queryService.isDuplicateEmail(any(Long.class), any(String.class)))
                .willReturn(true);
      BDDMockito.given(repository.save(any(Customer.class))).willReturn(expect);
      // when then
      assertThrows(BadRequestException.class, () -> service.update(1L, request));
    }

  }

  @Nested
  @DisplayName("회원 정보 획득 - getById")
  public class TestGetCustomerById {

    private Customer customer;

    @BeforeEach
    public void setUp() {
      customer = Customer.builder()
                         .id(1L)
                         .nickname("test")
                         .email("test@test.com")
                         .build();
    }

    @Test
    @DisplayName("정상 - 있는 정보 요청")
    public void test_normal_case() {
      // given
      Long id = 1L;
      CustomerResDto expected = CustomerResDto.builder()
                                              .id(customer.getId())
                                              .nickname(customer.getNickname())
                                              .email(customer.getEmail())
                                              .build();
      BDDMockito.given(repository.findById(any(Long.class))).willReturn(Optional.of(customer));
      BDDMockito.given(mapper.mapToResDto(any(Customer.class))).willReturn(expected);
      // when
      CustomerResDto result = assertDoesNotThrow(() -> service.getById(id));
      // then
      assertEquals(expected, result);
    }

    @Test
    @DisplayName("실패 - 없는 정보 요청")
    public void test_not_exist_case() {
      // given
      Long id = 1L;
      CustomerResDto expected = CustomerResDto.builder()
                                              .id(customer.getId())
                                              .nickname(customer.getNickname())
                                              .email(customer.getEmail())
                                              .build();
      BDDMockito.given(repository.findById(any(Long.class))).willReturn(Optional.empty());
      BDDMockito.given(mapper.mapToResDto(any(Customer.class))).willReturn(expected);
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
                         .id(1L)
                         .nickname("test")
                         .email("test@test.com")
                         .build();
    }

    @Test
    @DisplayName("정상 - 있는 정보 요청")
    public void test_normal_case() {
      // given
      Long id = 1L;
      CustomerResDto expected = CustomerResDto.builder()
                                              .id(customer.getId())
                                              .nickname(customer.getNickname())
                                              .email(customer.getEmail())
                                              .build();
      BDDMockito.given(repository.findByNickname(any(String.class)))
                .willReturn(Optional.of(customer));
      BDDMockito.given(mapper.mapToResDto(any(Customer.class))).willReturn(expected);
      // when
      CustomerResDto result = assertDoesNotThrow(
          () -> service.getByNickname(customer.getNickname()));
      // then
      assertEquals(expected, result);
    }

    @Test
    @DisplayName("실패 - 없는 정보 요청")
    public void test_not_exist_case() {
      // given
      Long id = 1L;
      CustomerResDto expected = CustomerResDto.builder()
                                              .id(customer.getId())
                                              .nickname(customer.getNickname())
                                              .email(customer.getEmail())
                                              .build();
      BDDMockito.given(repository.findByNickname(any(String.class))).willReturn(Optional.empty());
      BDDMockito.given(mapper.mapToResDto(any(Customer.class))).willReturn(expected);
      // when
      assertThrows(NotFoundException.class, () -> service.getByNickname("None"));
    }
  }

  @Nested
  @DisplayName("회원 정보 획득 - getByEmail")
  public class TestGetCustomerByEmail {

    private Customer customer;

    @BeforeEach
    public void setUp() {
      customer = Customer.builder()
                         .id(1L)
                         .nickname("test")
                         .email("test@test.com")
                         .build();
    }

    @Test
    @DisplayName("정상 - 있는 정보 요청")
    public void test_normal_case() {
      // given
      Long id = 1L;
      CustomerResDto expected = CustomerResDto.builder()
                                              .id(customer.getId())
                                              .nickname(customer.getNickname())
                                              .email(customer.getEmail())
                                              .build();
      BDDMockito.given(repository.findByEmail(any(String.class)))
                .willReturn(Optional.of(customer));
      BDDMockito.given(mapper.mapToResDto(any(Customer.class))).willReturn(expected);
      // when
      CustomerResDto result = assertDoesNotThrow(
          () -> service.getByEmail(customer.getNickname()));
      // then
      assertEquals(expected, result);
    }

    @Test
    @DisplayName("실패 - 없는 정보 요청")
    public void test_not_exist_case() {
      // given
      Long id = 1L;
      CustomerResDto expected = CustomerResDto.builder()
                                              .id(customer.getId())
                                              .nickname(customer.getNickname())
                                              .email(customer.getEmail())
                                              .build();
      BDDMockito.given(repository.findByEmail(any(String.class))).willReturn(Optional.empty());
      BDDMockito.given(mapper.mapToResDto(any(Customer.class))).willReturn(expected);
      // when
      assertThrows(NotFoundException.class, () -> service.getByEmail("None"));
    }
  }
}
