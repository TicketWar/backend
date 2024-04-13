package com.ticketwar.ticketwar.customer.service;

import com.ticketwar.ticketwar.customer.dto.CustomerReqDto;
import com.ticketwar.ticketwar.customer.dto.CustomerResDto;
import com.ticketwar.ticketwar.customer.entity.Customer;
import com.ticketwar.ticketwar.customer.repository.CustomerRepository;
import com.ticketwar.ticketwar.customer.utils.CustomerMapper;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
    this.customerMapper = new CustomerMapper();
  }

  /**
   * 회원가입시 로직
   * NOTE: 이후 return value 조정이 필요함.
   *
   * @param customerReqDto
   * @return
   * @throws BadRequestException
   */
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

  /**
   * 회원 정보 업데이트.
   * NOTE: 이후 Return value 변경 필요함.
   *
   * @param id
   * @param customerReqDto
   * @return
   * @throws BadRequestException
   */
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
   * id 를 기반으로 회원 정보를 조회합니다.
   *
   * @param id
   * @return
   * @throws NotFoundException
   */
  public CustomerResDto getById(Long id) throws NotFoundException {
    return customerRepository.findById(id)
                             .map(customerMapper::mapToResDto)
                             .orElseThrow(NotFoundException::new);
  }

  /**
   * nickname 을 기반으로 회원 정보를 조회합니다.
   *
   * @param nickname
   * @return
   * @throws NotFoundException
   */
  public CustomerResDto getByNickname(String nickname) throws NotFoundException {
    return customerRepository.findByNickname(nickname)
                             .map(customerMapper::mapToResDto)
                             .orElseThrow(NotFoundException::new);
  }

  /**
   * email 을 기반으로 회원 정보를 조회합니다.
   *
   * @param email
   * @return
   * @throws NotFoundException
   */
  public CustomerResDto getByEmail(String email) throws NotFoundException {
    return customerRepository.findByEmail(email)
                             .map(customerMapper::mapToResDto)
                             .orElseThrow(NotFoundException::new);
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
