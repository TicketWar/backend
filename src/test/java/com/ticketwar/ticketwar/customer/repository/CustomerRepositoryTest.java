package com.ticketwar.ticketwar.customer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ticketwar.ticketwar.customer.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CustomerRepositoryTest {

  @Autowired
  private CustomerRepository repository;
  private Customer customer;
  private Customer customerInvalid;

  @BeforeEach
  public void setUp() {
    customer = Customer.builder()
                       .nickname("sungjpar")
                       .email("sungjpar@test.com")
                       .build();
    customerInvalid = Customer.builder()
                              .nickname("test")
                              .email("test@test.com")
                              .build();
  }

  @DisplayName("Test find by id")
  @Test
  public void test_find_by_id() {
    Customer saved = repository.saveAndFlush(customer);
    Customer c = repository.findById(saved.getId()).orElse(customerInvalid);

    assertEquals(c, customer);
  }

  @DisplayName("Test find by Nickname")
  @Test
  public void test_find_by_nickname() {
    Customer saved = repository.saveAndFlush(customer);
    Customer c = repository.findByNickname(saved.getNickname()).orElse(customerInvalid);

    assertEquals(customer.getEmail(), c.getEmail());
    assertEquals(customer.getNickname(), c.getNickname());
    assertEquals(saved.getId(), c.getId());
  }

  @DisplayName("Test find by Nickname not exist case")
  @Test
  public void test_find_by_nickname_not_exist() {
    Customer saved = repository.saveAndFlush(customer);

    assertEquals(repository.findByNickname("test").isPresent(), false);
  }
}
