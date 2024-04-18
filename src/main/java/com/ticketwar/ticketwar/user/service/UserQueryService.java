package com.ticketwar.ticketwar.user.service;

import com.ticketwar.ticketwar.user.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * UserQueryService
 * <p>
 * DB 에서의 결과 조회 / 업데이트에 대해서 로직이 있는 경우, 해당 서비스에서 처리합니다.
 * <p>
 * 데이터 처리 관련 로직에 대한 분리가 목적입니다.
 */
@Service
public class UserQueryService {

  private final UserRepository userRepository;

  public UserQueryService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * 기존 ID 를 제외하고 같은 nickname 을 가진 user 가 있는지 확인합니다.
   *
   * @param id
   * @param nickname
   * @return 존재할 경우 true
   */
  public boolean isDuplicateNickname(Long id, String nickname) {
    return userRepository.findByNickname(nickname)
                         .map(user -> user.getId() != id)
                         .orElse(false);
  }

  /**
   * 해당 nickname을 가진 user가 있는지 확인합니다.
   *
   * @param nickname
   * @return 존재할 경우 true
   */
  public boolean isDuplicateNickname(String nickname) {
    return userRepository.findByNickname(nickname).isPresent();
  }

  /**
   * 기존 ID를 제외하고, 해당 email 을 가진 user 있는지 확인합니다.
   *
   * @param id
   * @param email
   * @return 존재할 경우 true
   */
  public boolean isDuplicateEmail(Long id, String email) {
    return userRepository.findByEmail(email)
                         .map(user -> user.getId() != id)
                         .orElse(false);
  }

  /**
   * 해당 email을 가진 user가 있는지 확인합니다.
   *
   * @param email
   * @return 존재할 경우 true
   */
  public boolean isDuplicateEmail(String email) {
    return userRepository.findByEmail(email).isPresent();
  }
}
