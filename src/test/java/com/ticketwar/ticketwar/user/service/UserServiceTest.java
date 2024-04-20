package com.ticketwar.ticketwar.user.service;

import com.ticketwar.ticketwar.exception.CustomException;
import com.ticketwar.ticketwar.exception.ExceptionStatus;
import com.ticketwar.ticketwar.user.dto.UserReqDto;
import com.ticketwar.ticketwar.user.dto.UserResDto;
import com.ticketwar.ticketwar.user.entity.User;
import com.ticketwar.ticketwar.user.repository.UserRepository;
import com.ticketwar.ticketwar.user.utils.UserMapper;
import jakarta.transaction.Transactional;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Transactional
class UserServiceTest {

  @InjectMocks
  private UserService service;
  @Mock
  private UserRepository repository;
  @Mock
  private UserQueryService queryService;
  @Mock
  private UserMapper mapper;

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
      UserReqDto request = UserReqDto.builder()
          .nickname(nickname)
          .email(email)
          .password("password")
          .build();
      User expect = User.builder()
          .id(1L)
          .nickname(nickname)
          .email(email)
          .password("password")
          .build();
      BDDMockito.given(queryService.isDuplicateNickname(any(String.class))).willReturn(false);
      BDDMockito.given(queryService.isDuplicateEmail(any(String.class))).willReturn(false);
      BDDMockito.given(repository.save(any(User.class))).willReturn(expect);
      // when
      User newUser = assertDoesNotThrow(() -> service.signUp(request));
      assertEquals(expect, newUser);
    }

    @DisplayName("실패 - 닉네임 중복")
    @Test
    public void test_sign_up_duplicate_nickname() {
      // given
      UserReqDto request = UserReqDto.builder()
          .nickname("sungjpar")
          .email("email@email.com")
          .password("password")
          .build();
      BDDMockito.given(queryService.isDuplicateNickname(any(String.class))).willReturn(true);
      // when, then
      assertThrows(CustomException.class, () -> service.signUp(request), ExceptionStatus.DUPLICATED_NICKNAME.getMsg());
    }

    @DisplayName("실패 - 이메일 중복")
    @Test
    public void test_sign_up_duplicate_email() {      // given
      UserReqDto request = UserReqDto.builder()
          .nickname("sungjpar")
          .email("email@email.com")
          .password("password")
          .build();
      BDDMockito.given(queryService.isDuplicateNickname(any(String.class))).willReturn(false);
      BDDMockito.given(queryService.isDuplicateEmail(any(String.class))).willReturn(true);
      // when, then
      assertThrows(CustomException.class, () -> service.signUp(request), ExceptionStatus.DUPLICATED_EMAIL.getMsg());
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
      UserReqDto request = UserReqDto.builder()
          .nickname(nickname)
          .email(email)
          .password("password")
          .build();
      User expect = User.builder()
          .id(1L)
          .nickname(nickname)
          .email(email)
          .password("password")
          .build();
      BDDMockito.given(repository.findById(any(Long.class))).willReturn(Optional.of(expect));
      BDDMockito.given(queryService.isDuplicateNickname(any(Long.class), any(String.class)))
          .willReturn(false);
      BDDMockito.given(queryService.isDuplicateEmail(any(Long.class), any(String.class)))
          .willReturn(false);
      BDDMockito.given(repository.save(any(User.class))).willReturn(expect);
      // when
      User updatedUser = assertDoesNotThrow(() -> service.update(1L, request));
      // then
      assertEquals(expect, updatedUser);
    }

    @Test
    @DisplayName("실패 - 닉네임 중복")
    public void test_duplicate_nickname() {
      // given
      String nickname = "sungjpar";
      String email = "sungjpar@student.42seoul.kr";
      Long id = 1L;
      UserReqDto request = UserReqDto.builder()
          .nickname(nickname)
          .email(email)
          .password("password")
          .build();
      User expect = User.builder()
          .id(1L)
          .nickname(nickname)
          .email(email)
          .password("password")
          .build();
      BDDMockito.given(repository.findById(any(Long.class))).willReturn(Optional.of(expect));
      BDDMockito.given(queryService.isDuplicateNickname(any(Long.class), any(String.class)))
          .willReturn(true);
      BDDMockito.given(queryService.isDuplicateEmail(any(Long.class), any(String.class)))
          .willReturn(false);
      BDDMockito.given(repository.save(any(User.class))).willReturn(expect);
      // when then
      assertThrows(CustomException.class, () -> service.update(1L, request), ExceptionStatus.DUPLICATED_NICKNAME.getMsg());
    }

    @Test
    @DisplayName("실패 - 이메일 중복")
    public void test_duplicate_email() {
      // given
      String nickname = "sungjpar";
      String email = "sungjpar@student.42seoul.kr";
      Long id = 1L;
      UserReqDto request = UserReqDto.builder()
          .nickname(nickname)
          .email(email)
          .password("password")
          .build();
      User expect = User.builder()
          .id(1L)
          .nickname(nickname)
          .email(email)
          .password("password")
          .build();
      BDDMockito.given(repository.findById(any(Long.class))).willReturn(Optional.of(expect));
      BDDMockito.given(queryService.isDuplicateNickname(any(Long.class), any(String.class)))
          .willReturn(false);
      BDDMockito.given(queryService.isDuplicateEmail(any(Long.class), any(String.class)))
          .willReturn(true);
      BDDMockito.given(repository.save(any(User.class))).willReturn(expect);
      // when then
      assertThrows(CustomException.class, () -> service.update(1L, request), ExceptionStatus.DUPLICATED_EMAIL.getMsg());
    }

  }

  @Nested
  @DisplayName("회원 정보 획득 - getById")
  public class TestGetUserById {

    private User user;

    @BeforeEach
    public void setUp() {
      user = User.builder()
          .id(1L)
          .nickname("test")
          .email("test@test.com")
          .password("password")
          .build();
    }

    @Test
    @DisplayName("정상 - 있는 정보 요청")
    public void test_normal_case() {
      // given
      Long id = 1L;
      UserResDto expected = UserResDto.builder()
          .id(user.getId())
          .nickname(user.getNickname())
          .email(user.getEmail())
          .build();
      BDDMockito.given(repository.findById(any(Long.class))).willReturn(Optional.of(user));
      BDDMockito.given(mapper.mapToResDto(any(User.class))).willReturn(expected);
      // when
      UserResDto result = assertDoesNotThrow(() -> service.getById(id));
      // then
      assertEquals(expected, result);
    }

    @Test
    @DisplayName("실패 - 없는 정보 요청")
    public void test_not_exist_case() {
      // given
      Long id = 1L;
      UserResDto expected = UserResDto.builder()
          .id(user.getId())
          .nickname(user.getNickname())
          .email(user.getEmail())
          .build();
      BDDMockito.given(repository.findById(any(Long.class))).willReturn(Optional.empty());
      BDDMockito.given(mapper.mapToResDto(any(User.class))).willReturn(expected);
      // when
      assertThrows(CustomException.class, () -> service.getById(999L), ExceptionStatus.USER_NOT_FOUND.getMsg());
    }
  }

  @Nested
  @DisplayName("회원 정보 획득 - getByNickname")
  public class TestGetUserByNickname {

    private User user;

    @BeforeEach
    public void setUp() {
      user = User.builder()
          .id(1L)
          .nickname("test")
          .email("test@test.com")
          .password("password")
          .build();
    }

    @Test
    @DisplayName("정상 - 있는 정보 요청")
    public void test_normal_case() {
      // given
      Long id = 1L;
      UserResDto expected = UserResDto.builder()
          .id(user.getId())
          .nickname(user.getNickname())
          .email(user.getEmail())
          .build();
      BDDMockito.given(repository.findByNickname(any(String.class)))
          .willReturn(Optional.of(user));
      BDDMockito.given(mapper.mapToResDto(any(User.class))).willReturn(expected);
      // when
      UserResDto result = assertDoesNotThrow(
          () -> service.getByNickname(user.getNickname()));
      // then
      assertEquals(expected, result);
    }

    @Test
    @DisplayName("실패 - 없는 정보 요청")
    public void test_not_exist_case() {
      // given
      Long id = 1L;
      UserResDto expected = UserResDto.builder()
          .id(user.getId())
          .nickname(user.getNickname())
          .email(user.getEmail())
          .build();
      BDDMockito.given(repository.findByNickname(any(String.class))).willReturn(Optional.empty());
      BDDMockito.given(mapper.mapToResDto(any(User.class))).willReturn(expected);
      // when
      assertThrows(CustomException.class, () -> service.getByNickname("None"), ExceptionStatus.USER_NOT_FOUND.getMsg());
    }
  }

  @Nested
  @DisplayName("회원 정보 획득 - getByEmail")
  public class TestGetUserByEmail {

    private User user;

    @BeforeEach
    public void setUp() {
      user = User.builder()
          .id(1L)
          .nickname("test")
          .email("test@test.com")
          .password("password")
          .build();
    }

    @Test
    @DisplayName("정상 - 있는 정보 요청")
    public void test_normal_case() {
      // given
      Long id = 1L;
      UserResDto expected = UserResDto.builder()
          .id(user.getId())
          .nickname(user.getNickname())
          .email(user.getEmail())
          .build();
      BDDMockito.given(repository.findByEmail(any(String.class)))
          .willReturn(Optional.of(user));
      BDDMockito.given(mapper.mapToResDto(any(User.class))).willReturn(expected);
      // when
      UserResDto result = assertDoesNotThrow(
          () -> service.getByEmail(user.getNickname()));
      // then
      assertEquals(expected, result);
    }

    @Test
    @DisplayName("실패 - 없는 정보 요청")
    public void test_not_exist_case() {
      // given
      Long id = 1L;
      UserResDto expected = UserResDto.builder()
          .id(user.getId())
          .nickname(user.getNickname())
          .email(user.getEmail())
          .build();
      BDDMockito.given(repository.findByEmail(any(String.class))).willReturn(Optional.empty());
      BDDMockito.given(mapper.mapToResDto(any(User.class))).willReturn(expected);
      // when
      assertThrows(CustomException.class, () -> service.getByEmail("None"), ExceptionStatus.USER_NOT_FOUND.getMsg());
    }
  }
}
