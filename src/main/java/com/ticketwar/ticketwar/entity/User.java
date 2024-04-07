package com.ticketwar.ticketwar.entity;

import com.ticketwar.ticketwar.entity.trackable.CreatedAndUpdatedTimeTrackable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@EqualsAndHashCode(callSuper = true) @ToString
@RequiredArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_table") // h2 예약어 문제인듯. user 로 하니까 터짐.
public class User extends CreatedAndUpdatedTimeTrackable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "nickname", columnDefinition = "varchar(20)")
    private @NonNull String nickname;

    @Column(name = "email", columnDefinition = "varchar(100)")
    private @NonNull String email;
}
