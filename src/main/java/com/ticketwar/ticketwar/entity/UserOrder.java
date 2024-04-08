package com.ticketwar.ticketwar.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "USER_ORDER")
public class UserOrder {
    //주문 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    //유저 id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    //주문일자
    @CreationTimestamp
    @Column(name = "ordered_at", nullable = false, updatable = false)
    private LocalDateTime ordered_at;

    @Builder
    public UserOrder(User user, LocalDateTime ordered_at){
        this.user = user;
        this.ordered_at = ordered_at;
    }
}
