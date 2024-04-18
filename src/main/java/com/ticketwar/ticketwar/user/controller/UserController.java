package com.ticketwar.ticketwar.user.controller;

import com.ticketwar.ticketwar.user.dto.UserReqDto;
import com.ticketwar.ticketwar.user.dto.UserResDto;
import com.ticketwar.ticketwar.user.entity.User;
import com.ticketwar.ticketwar.user.service.UserService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Customer 관련 정보에 대한 Rest Controller
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  // 이후 auth controller 쪽으로 빠져야함.
  @PostMapping("/signup")
  public ResponseEntity<UserResDto> signUp(@RequestBody UserReqDto userReqDto)
      throws BadRequestException {
    User newUser = userService.signUp(userReqDto);
    URI location = UriComponentsBuilder
        .fromPath("/users/" + newUser.getId())
        .build().toUri();

    return ResponseEntity.created(location).build();
  }

  @PatchMapping("/{id}")
  public User update(@PathVariable("id") Long id, @RequestBody UserReqDto userReqDto)
      throws BadRequestException {
    return userService.update(id, userReqDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResDto> getById(@PathVariable("id") Long id)
      throws NotFoundException {
    return ResponseEntity.ok(userService.getById(id));
  }

  @GetMapping("/nickname/{nickname}")
  public UserResDto getByNickname(@PathVariable("nickname") String nickname)
      throws NotFoundException {
    return userService.getByNickname(nickname);
  }

  @GetMapping("/email/{email}")
  public UserResDto getByEmail(@PathVariable("email") String email)
      throws NotFoundException {
    return userService.getByEmail(email);
  }
}
