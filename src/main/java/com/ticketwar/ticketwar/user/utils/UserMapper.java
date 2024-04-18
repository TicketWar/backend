package com.ticketwar.ticketwar.user.utils;

import com.ticketwar.ticketwar.user.dto.UserResDto;
import com.ticketwar.ticketwar.user.entity.User;

/**
 * User Entity를 DTO 로 변환하는 유틸리티 클래스
 */
public class UserMapper {

  public UserResDto mapToResDto(User user) {
    return UserResDto.builder()
                     .id(user.getId())
                     .nickname(user.getNickname())
                     .email(user.getEmail())
                     .build();
  }
}
