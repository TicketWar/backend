package com.ticketwar.ticketwar.customer.service;

import com.ticketwar.ticketwar.customer.dto.AddCustomerRequest;
import com.ticketwar.ticketwar.customer.dto.CustomerDto;
import com.ticketwar.ticketwar.customer.dto.UpdateCustomerRequest;
import com.ticketwar.ticketwar.customer.entity.Customer;
import com.ticketwar.ticketwar.customer.exception.CustomerNotFoundException;
import com.ticketwar.ticketwar.customer.repository.CustomerRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public boolean signUp(CustomerDto customerDto) {
    // TODO: 중복 회원 검증 ... 기타 등등
    try {
      Customer customer =
          customerRepository.save(
              Customer.builder().nickname("김민규").email("kimminkyeu@gmail.com").build());
    } catch (DataAccessException e) {
      return false;
    }
    return true;
  }

  // 저장
  public Customer save(AddCustomerRequest request) {
    return customerRepository.save(request.toEntity());
  }

  // 전부 조회
  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  // id 조회
  public Customer findById(Long id) {
    return customerRepository
        .findById(id)
        .orElseThrow(() -> new CustomerNotFoundException("not found: " + id));
  }

  // id로 삭제
  public void delete(Long id) {
    customerRepository.deleteById(id);
  }

  // id로 수정
  public Customer update(Long id, UpdateCustomerRequest request) {
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new CustomerNotFoundException("not found: " + id));
    customer.update(request.getNickname(), request.getEmail());
    return customer;
  }
}
