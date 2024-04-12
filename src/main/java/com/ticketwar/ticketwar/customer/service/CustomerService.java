package com.ticketwar.ticketwar.customer.service;

import com.ticketwar.ticketwar.customer.entity.Customer;
import com.ticketwar.ticketwar.customer.repository.CustomerRepository;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("min_version")
@Service("min_CustomerService")
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public void deleteById(Long id) {
    customerRepository.deleteById(id);
  }

  public void create(String nickname, String email) {
    customerRepository.save(Customer.builder()
                                    .nickname(nickname)
                                    .email(email)
                                    .build());
  }

  public void update(Long id, String nickname, String email) {
    customerRepository.findById(id).map((customer) -> {
          customer.setNickname(nickname); // error
          customer.setEmail(email); // error
          return;
        }
    );
  }

  public Optional<Customer> findById(Long id) {
    return customerRepository.findById(id);
  }

  public boolean existsByEmail(String email) {
    return customerRepository.existsByEmail(email);
  }

  public boolean existsByNickname(String nickname) {
    return customerRepository.existsByNickname(nickname);
  }
}
