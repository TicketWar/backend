package com.ticketwar.ticketwar.customer.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("min_version")
@SpringBootTest
public class CustomerServiceTest {

  @Autowired
  private CustomerService customerService;

  @Test
  @DisplayName("테스트 명")
  public void test() {
    // ...
  }
}
