package com.ticketwar.ticketwar.repository;

import com.ticketwar.ticketwar.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
