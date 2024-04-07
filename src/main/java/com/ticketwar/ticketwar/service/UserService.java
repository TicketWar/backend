package com.ticketwar.ticketwar.service;

import com.ticketwar.ticketwar.dto.UserDto;
import com.ticketwar.ticketwar.entity.User;
import com.ticketwar.ticketwar.repository.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean signUp(UserDto userDto) {
        // TODO: 중복 회원 검증 ... 기타 등등
        User user = new User(userDto.getNickname(), userDto.getEmail());
        try {
            User saved = userRepository.save(user);
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }
}
