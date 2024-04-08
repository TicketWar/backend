package com.ticketwar.ticketwar.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "USER")
public class User {
    //유저id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long id;

    //nickname
    @Column(name = "nickname", nullable = false, length = 20, unique = true)
    private String nickname;

    //email
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    //생성일자
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    //수정일자
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = false)
    private LocalDateTime updated_at = LocalDateTime.now();

    @Builder
    public User(String nickname, String email) {
        this.nickname =nickname;
        this.email = email;
    }
}
