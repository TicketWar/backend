package com.ticketwar.ticketwar.user.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import com.ticketwar.ticketwar.user.entity.User;
import com.ticketwar.ticketwar.user.repository.UserRepository;
import java.util.Optional;
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserQueryServiceTest {

  @InjectMocks
  private UserQueryService service;
  @Mock
  private UserRepository repository;

  @Nested
  @DisplayName("닉네임 중복 검사")
  public class Test_is_duplicate_nickname {

    private User user;

    @BeforeEach
    @Test
    public void setUp() {
      user = User.builder()
                 .id(1L)
                 .nickname("test")
                 .email("test@test.com")
                 .password("password")
                 .build();
    }

    @Nested
    @DisplayName("ID 없이 단순 체크")
    public class Test_case_without_id {

      @DisplayName("정상 - 비중복 닉네임")
      @Test
      public void test_normal_case() {
        // given
        BDDMockito.given(repository.findByNickname(any(String.class))).willReturn(Optional.empty());
        // when
        boolean result = service.isDuplicateNickname("not_exist_nickname");
        // then
        assertFalse(result);
      }

      @DisplayName("실패 - 중복 닉네임")
      @Test
      public void test_duplicated_case() {
        // given
        BDDMockito.given(repository.findByNickname(any(String.class)))
                  .willReturn(Optional.of(user));
        // when
        boolean result = service.isDuplicateNickname(user.getNickname());
        // then
        assertTrue(result);
      }
    }

    @Nested
    @DisplayName("해당 ID 포함 체크")
    public class Test_case_with_id {

      @DisplayName("정상 - 비중복 닉네임")
      @Test
      public void test_normal_case() {
        // given
        BDDMockito.given(repository.findByNickname(any(String.class))).willReturn(Optional.empty());
        // when
        boolean result = service.isDuplicateNickname(user.getId(), "not_exist");
        // then
        assertFalse(result);
      }

      @DisplayName("정상 - 동일 닉네임")
      @Test
      public void test_identical_nickname() {
        // given
        BDDMockito.given(repository.findByNickname(any(String.class)))
                  .willReturn(Optional.of(user));
        // when
        boolean result = service.isDuplicateNickname(user.getId(), user.getNickname());
        // then
        assertFalse(result);
      }

      @Test
      public void test_duplicated_case() {
        // given
        User duplicatedUser = User.builder()
                                  .id(2L)
                                  .nickname("test2")
                                  .email("test2@test.com")
                                  .password("test")
                                  .build();
        BDDMockito.given(repository.findByNickname(any(String.class)))
                  .willReturn(Optional.of(duplicatedUser));
        // when
        boolean result = service.isDuplicateNickname(user.getId(), user.getNickname());
        // then
        assertTrue(result);
      }
    }
  }

  @Nested
  @DisplayName("이메일 중복 검사")
  public class Test_is_duplicate_email {

    private User user;

    @BeforeEach
    @Test
    public void setUp() {
      user = User.builder()
                 .id(1L)
                 .nickname("test")
                 .email("test@test.com")
                 .password("password")
                 .build();
    }

    @Nested
    @DisplayName("ID 없이 단순 체크")
    public class Test_case_without_id {

      @DisplayName("정상 - 비중복 이메일")
      @Test
      public void test_normal_case() {
        // given
        BDDMockito.given(repository.findByEmail(any(String.class))).willReturn(Optional.empty());
        // when
        boolean result = service.isDuplicateEmail("not_exist_email@email.com");
        // then
        assertFalse(result);
      }

      @DisplayName("실패 - 중복 이메일")
      @Test
      public void test_duplicated_case() {
        // given
        BDDMockito.given(repository.findByEmail(any(String.class)))
                  .willReturn(Optional.of(user));
        // when
        boolean result = service.isDuplicateEmail(user.getEmail());
        // then
        assertTrue(result);
      }
    }

    @Nested
    @DisplayName("해당 ID 포함 체크")
    public class Test_case_with_id {

      @DisplayName("정상 - 비중복 이메일")
      @Test
      public void test_normal_case() {
        // given
        BDDMockito.given(repository.findByEmail(any(String.class))).willReturn(Optional.empty());
        // when
        boolean result = service.isDuplicateEmail(user.getId(), "not_exist_email@email.com");
        // then
        assertFalse(result);
      }

      @DisplayName("정상 - 동일 이메일")
      @Test
      public void test_identical_nickname() {
        // given
        BDDMockito.given(repository.findByEmail(any(String.class)))
                  .willReturn(Optional.of(user));
        // when
        boolean result = service.isDuplicateEmail(user.getId(), user.getEmail());
        // then
        assertFalse(result);
      }

      @Test
      public void test_duplicated_case() {
        // given
        User duplicatedUser = User.builder()
                                  .id(2L)
                                  .nickname("test2")
                                  .email("test2@test.com")
                                  .password("test")
                                  .build();
        BDDMockito.given(repository.findByEmail(any(String.class)))
                  .willReturn(Optional.of(duplicatedUser));
        // when
        boolean result = service.isDuplicateEmail(user.getId(), user.getEmail());
        // then
        assertTrue(result);
      }
    }
  }
}