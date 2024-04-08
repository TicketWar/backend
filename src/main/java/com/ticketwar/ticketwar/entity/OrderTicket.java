package com.ticketwar.ticketwar.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ORDER_TICKET")
public class OrderTicket {

    //orderTicket id 는 필요없나?
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    //티켓 id
    @OneToOne
    @JoinColumn(name = "ticket_id", nullable = false, updatable = false)
    Ticket ticket;

    //주문 id
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    UserOrder userOrder;

    @Builder
    public OrderTicket(Ticket ticket,UserOrder userOrder) {
        this.ticket = ticket;
        this.userOrder = userOrder;
    }
}
