package com.ticketwar.ticketwar.order.repository;

import com.ticketwar.ticketwar.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
