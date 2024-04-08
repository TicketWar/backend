package com.ticketwar.ticketwar.customer.repository;

import com.ticketwar.ticketwar.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
