package com.ticketwar.ticketwar.customer.service;

import com.ticketwar.ticketwar.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

/**
 * CustomerQueryService
 * <p>
 * DB 에서의 결과 조회 / 업데이트에 대해서 로직이 있는 경우, 해당 서비스에서 처리합니다.
 * <p>
 * 데이터 처리 관련 로직에 대한 분리가 목적입니다.
 */
@Service
public class CustomerQueryService {

  private final CustomerRepository customerRepository;

  public CustomerQueryService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  /**
   * 기존 ID 를 제외하고 같은 nickname 을 가진 customer 가 있는지 확인합니다.
   *
   * @param id
   * @param nickname
   * @return 존재할 경우 true
   */
  public boolean isDuplicateNickname(Long id, String nickname) {
    return customerRepository.findByNickname(nickname)
                             .map(customer -> customer.getId() != id)
                             .orElse(false);
  }

  /**
   * 해당 nickname을 가진 customer가 있는지 확인합니다.
   *
   * @param nickname
   * @return 존재할 경우 true
   */
  public boolean isDuplicateNickname(String nickname) {
    return customerRepository.findByNickname(nickname).isPresent();
  }

  /**
   * 기존 ID를 제외하고, 해당 email 을 가진 customer가 있는지 확인합니다.
   *
   * @param id
   * @param email
   * @return 존재할 경우 true
   */
  public boolean isDuplicateEmail(Long id, String email) {
    return customerRepository.findByEmail(email)
                             .map(customer -> customer.getId() != id)
                             .orElse(false);
  }

  /**
   * 해당 email을 가진 customer가 있는지 확인합니다.
   *
   * @param email
   * @return 존재할 경우 true
   */
  public boolean isDuplicateEmail(String email) {
    return customerRepository.findByEmail(email).isPresent();
  }
}
