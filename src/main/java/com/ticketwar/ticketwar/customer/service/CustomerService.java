package com.ticketwar.ticketwar.customer.service;

import com.ticketwar.ticketwar.customer.dto.CustomerReqDto;
import com.ticketwar.ticketwar.customer.entity.Customer;
import com.ticketwar.ticketwar.customer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  // 이후에 return type 변경할 것.
  @Transactional
  public boolean signUp(CustomerReqDto customerReqDto) throws BadRequestException {
    final String nickname = customerReqDto.getNickname();
    final String email = customerReqDto.getEmail();

    if (isDuplicateNickname(nickname)) {
      throw new BadRequestException("Duplicated nickname");
    }
    if (isDuplicateEmail(email)) {
      throw new BadRequestException("Duplicated email");
    }

    final Customer newCustomer = customerReqDto.toEntity();
    customerRepository.save(newCustomer);
    return true;
  }

  @Transactional
  public boolean update(Long id, CustomerReqDto customerReqDto) throws BadRequestException {
    final Customer customer = customerRepository.findById(id)
                                                .orElseThrow(() -> new BadRequestException(
                                                    "Not exist customer id"));
    final String updateNickname = customerReqDto.getNickname();
    final String updateEmail = customerReqDto.getEmail();

    if (isDuplicateNickname(id, updateNickname)) {
      throw new BadRequestException("Duplicated nickname");
    }
    if (isDuplicateEmail(id, updateEmail)) {
      throw new BadRequestException("Duplicated email");
    }
    customer.setNickname(updateNickname);
    customer.setEmail(updateEmail);
    return true;
  }

  /**
   * 기존 ID 를 제외하고 같은 nickname 을 가진 customer 가 있는지 확인합니다.
   *
   * @param id
   * @param nickname
   * @return 존재할 경우 true
   */
  private boolean isDuplicateNickname(Long id, String nickname) {
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
  private boolean isDuplicateNickname(String nickname) {
    return customerRepository.findByNickname(nickname).isPresent();
  }

  /**
   * 기존 ID를 제외하고, 해당 email 을 가진 customer가 있는지 확인합니다.
   *
   * @param id
   * @param email
   * @return 존재할 경우 true
   */
  private boolean isDuplicateEmail(Long id, String email) {
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
  private boolean isDuplicateEmail(String email) {
    return customerRepository.findByEmail(email).isPresent();
  }
}
