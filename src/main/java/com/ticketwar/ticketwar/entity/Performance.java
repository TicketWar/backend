package com.ticketwar.ticketwar.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "PERFORMANCE")
public class Performance {

    // 공연 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    //공연이름
    @Column(name = "name")
    private String name;

    //티켓팅 시작
    @Column(name = "start_at")
    private LocalDateTime start_at;

    //티켓팅 종료
    @Column(name = "end_at")
    private LocalDateTime end_at;

    //생성일자
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;

    //수정일자
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @Builder
    public Performance(String name, LocalDateTime start_at, LocalDateTime end_at) {
        this.name = name;
        this.start_at = start_at;
        this.end_at = end_at;
    }

}
