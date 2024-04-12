package com.ticketwar.ticketwar.customer.repository;

import com.ticketwar.ticketwar.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;

//https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Meta(comment = "find customer by nickname, which is unique")
  Customer findByNickname(String nickname);

  @Meta(comment = "find by email, which is unique")
  Customer findByEmail(String email);

  @Meta(comment = "check if nickname exists")
  boolean existsByNickname(String nickname);

  @Meta(comment = "check if email exists")
  boolean existsByEmail(String email);
}
