package com.ticketwar.ticketwar.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE) // forbid setter
public class UserDto {
    private @NonNull String nickname;
    private @NonNull String email;
}
