package com.ticketwar.ticketwar.customer.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("min_version")
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

  @Autowired
  private CustomerController customerController;

  @Autowired
  MockMvc mockMvc;

  private static final String BASE_URL = "/customer";

  @Test
  @DisplayName("테스트 명")
  public void test() {
    //...
  }
}

