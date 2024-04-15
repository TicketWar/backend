package com.ticketwar.ticketwar.customer.repository;

import static org.assertj.core.api.Assertions.*;

import com.ticketwar.ticketwar.configure.P6SpySqlFormatter;
import com.ticketwar.ticketwar.customer.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@ImportAutoConfiguration(P6SpySqlFormatter.class)
class CustomerRepositoryTest {

  @Autowired
  private CustomerRepository repository;
  private Customer customer;

  @BeforeEach
  public void setUp() {
    customer = Customer.builder()
                       .nickname("sungjpar")
                       .email("sungjpar@test.com")
                       .build();
  }

  @DisplayName("Test find by id")
  @Test
  public void test_find_by_id() {
    Customer savedCustomer = repository.save(customer);

    assertThat(repository.findById(savedCustomer.getId()))
        .hasValue(savedCustomer);
  }

  @DisplayName("Test find by Nickname")
  @Test
  public void test_find_by_nickname() {
    Customer savedCustomer = repository.save(customer);

    assertThat(repository.findByNickname(savedCustomer.getNickname()))
        .hasValue(savedCustomer);
  }

  @DisplayName("Test find by Nickname not exist case")
  @Test
  public void test_find_by_nickname_not_exist() {

    assertThat(repository.findByNickname("test"))
        .isNotPresent();
  }
}
