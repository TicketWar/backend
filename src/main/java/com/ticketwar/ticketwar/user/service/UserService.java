package com.ticketwar.ticketwar.user.service;

import com.ticketwar.ticketwar.exception.CustomException;
import com.ticketwar.ticketwar.exception.ExceptionStatus;
import com.ticketwar.ticketwar.user.dto.UserReqDto;
import com.ticketwar.ticketwar.user.dto.UserResDto;
import com.ticketwar.ticketwar.user.entity.User;
import com.ticketwar.ticketwar.user.repository.UserRepository;
import com.ticketwar.ticketwar.user.utils.UserMapper;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserQueryService userQueryService;
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserService(UserQueryService userQueryService, UserRepository userRepository) {
    this.userQueryService = userQueryService;
    this.userRepository = userRepository;
    this.userMapper = new UserMapper();
  }

  /**
   * 회원가입시 로직 NOTE: 이후 return value 조정이 필요함.
   *
   * @param userReqDto
   * @return User
   * @throws CustomException
   */
  @Transactional
  public User signUp(UserReqDto userReqDto) throws CustomException {
    final String nickname = userReqDto.getNickname();
    final String email = userReqDto.getEmail();

    if (userQueryService.isDuplicateNickname(nickname)) {
      throw new CustomException(ExceptionStatus.DUPLICATE_NICKNAME);
    }
    if (userQueryService.isDuplicateEmail(email)) {
      throw new CustomException(ExceptionStatus.DUPLICATE_EMAIL);
    }
    return userRepository.save(userReqDto.toEntity());
  }

  /**
   * 회원 정보 업데이트. NOTE: 이후 Return value 변경 필요함.
   *
   * @param id
   * @param userReqDto
   * @return
   * @throws CustomException
   */
  @Transactional
  public User update(Long id, UserReqDto userReqDto) throws BadRequestException {
    final User user =
        userRepository.findById(id).orElseThrow(() -> new BadRequestException("Not exist user id"));
    final String updateNickname = userReqDto.getNickname();
    final String updateEmail = userReqDto.getEmail();

    if (userQueryService.isDuplicateNickname(id, updateNickname)) {
      throw new CustomException(ExceptionStatus.DUPLICATE_NICKNAME);
    }
    if (userQueryService.isDuplicateEmail(id, updateEmail)) {
      throw new CustomException(ExceptionStatus.DUPLICATE_EMAIL);
    }
    user.setNickname(updateNickname);
    user.setEmail(updateEmail);
    return userRepository.save(user);
  }

  /**
   * id 를 기반으로 회원 정보를 조회합니다.
   *
   * @param id
   * @return
   * @throws CustomException
   */
  public UserResDto getById(Long id) throws CustomException {
    return userRepository
        .findById(id)
        .map(userMapper::mapToResDto)
        .orElseThrow(() -> new CustomException(ExceptionStatus.USER_NOT_FOUND));
  }

  /**
   * nickname 을 기반으로 회원 정보를 조회합니다.
   *
   * @param nickname
   * @return
   * @throws CustomException
   */
  public UserResDto getByNickname(String nickname) throws CustomException {
    return userRepository
        .findByNickname(nickname)
        .map(userMapper::mapToResDto)
        .orElseThrow(() -> new CustomException(ExceptionStatus.USER_NOT_FOUND));
  }

  /**
   * email 을 기반으로 회원 정보를 조회합니다.
   *
   * @param email
   * @return
   * @throws CustomException
   */
  public UserResDto getByEmail(String email) throws CustomException {
    return userRepository
        .findByEmail(email)
        .map(userMapper::mapToResDto)
        .orElseThrow(() -> new CustomException(ExceptionStatus.USER_NOT_FOUND));
  }
}
