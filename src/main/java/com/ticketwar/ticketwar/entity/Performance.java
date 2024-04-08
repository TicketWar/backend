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
@Entity(name = "PERFORMANCE")
public class Performance {

    // 공연 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    //공연이름
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    //티켓팅 시작
    @Column(name = "start_at", nullable = false)
    private LocalDateTime start_at;

    //티켓팅 종료
    @Column(name = "end_at", nullable = false)
    private LocalDateTime end_at;

    //생성일자
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    //수정일자
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at = LocalDateTime.now();

    @Builder
    public Performance(String name, LocalDateTime start_at, LocalDateTime end_at) {
        this.name = name;
        this.start_at = start_at;
        this.end_at = end_at;
    }
}
