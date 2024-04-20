package com.ticketwar.ticketwar.user.controller;

import com.ticketwar.ticketwar.auth.domain.jwt.JwtData;
import com.ticketwar.ticketwar.auth.domain.jwt.JwtGuard;
import com.ticketwar.ticketwar.auth.domain.role.RoleGuard;
import com.ticketwar.ticketwar.auth.pass.Guard;
import com.ticketwar.ticketwar.auth.pass.UserData;
import com.ticketwar.ticketwar.user.dto.UserReqDto;
import com.ticketwar.ticketwar.user.dto.UserResDto;
import com.ticketwar.ticketwar.user.entity.User;
import com.ticketwar.ticketwar.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
  @Guard(value = RoleGuard.class, args = {"ADMIN"})
  public User update(@PathVariable("id") Long id, @RequestBody UserReqDto userReqDto)
      throws BadRequestException {
    return userService.update(id, userReqDto);
  }


  @GetMapping("/{id}")
  @Guard(JwtGuard.class)
  public ResponseEntity<UserResDto> getById(@UserData JwtData jwtData)
      throws NotFoundException {
    final Long id = jwtData.getId();
    return ResponseEntity.ok(userService.getById(id));
  }

  @GetMapping("/{id}")
  @Guard(value = RoleGuard.class, args = {"ADMIN"})
  public ResponseEntity<UserResDto> getById(
      @PathVariable("id") Long id
  )
      throws NotFoundException {
    return ResponseEntity.ok(userService.getById(id));
  }

  @GetMapping("/nickname/{nickname}")
  @Guard(value = RoleGuard.class, args = {"ADMIN"})
  public UserResDto getByNickname(@PathVariable("nickname") String nickname)
      throws NotFoundException {
    return userService.getByNickname(nickname);
  }

  @GetMapping("/email/{email}")
  @Guard(value = RoleGuard.class, args = {"ADMIN"})
  public UserResDto getByEmail(@PathVariable("email") String email)
      throws NotFoundException {
    return userService.getByEmail(email);
  }
}
