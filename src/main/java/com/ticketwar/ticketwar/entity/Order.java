package com.ticketwar.ticketwar.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@EqualsAndHashCode @ToString
@RequiredArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER_ORDER")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

//    @JoinColumn(name = "user_id", nullable = true)
    @ManyToOne // or zero
    @JoinColumn(name = "user_id")
    private @NonNull User user; // NOTE: ERD에서 userId가 비식별자. 그럼 없어도 되는 건가...?

    @Column(name = "ordered_at") // NOTE: 이건 created_at과 다른 건가?
    private @NonNull LocalDateTime orderedAt;

    @ManyToMany(mappedBy = "orders") // in Ticket.java
    Set<Ticket> tickets = new HashSet<>(); // NOTE: set일 필요 있음?
}
