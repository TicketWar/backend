package com.ticketwar.ticketwar.entity;

import com.ticketwar.ticketwar.entity.trackable.CreatedTimeTrackable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@EqualsAndHashCode(callSuper = true) @ToString
@RequiredArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TICKET")
public class Ticket extends CreatedTimeTrackable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @OneToOne // NOTE: nullable ??
    @JoinColumn(name = "seat_id")
    private @NonNull Seat seat;

    @ManyToOne // NOTE: nullable?? + many tickets have fk to one performance
    @JoinColumn(name = "performance_id")
    private @NonNull Performance performance;

    // NOTE: ManyToMany의 기본 fetchtype = eager. lazy로 하려면 @Transactional이 붙어야 함.
    @ManyToMany // ORDER_TICKET join table : ticket.addOrder(new Order(...));
    @JoinTable(name = "ORDER_TICKET"
            , joinColumns = @JoinColumn(name = "ticket_id")
            , inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Order> orders = new HashSet<>(); // NOTE: Set으로 안해도 되지 안음?
}
