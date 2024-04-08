package com.ticketwar.ticketwar.customer.service;

import com.ticketwar.ticketwar.customer.dto.CustomerDto;
import com.ticketwar.ticketwar.customer.entity.Customer;
import com.ticketwar.ticketwar.customer.repository.CustomerRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public boolean signUp(CustomerDto customerDto) {
    // TODO: 중복 회원 검증 ... 기타 등등
    Customer customer =
        customerRepository.save(
            Customer.builder().nickname("김민규").email("kimminkyeu@gmail.com").build());
    try {
      Customer saved = customerRepository.save(customer);
    } catch (DataAccessException e) {
      return false;
    }
    return true;
  }
}
