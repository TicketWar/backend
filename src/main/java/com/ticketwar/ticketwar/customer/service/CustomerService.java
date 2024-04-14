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

  private final CustomerQueryService customerQueryService;
  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  public CustomerService(CustomerQueryService customerQueryService,
      CustomerRepository customerRepository) {
    this.customerQueryService = customerQueryService;
    this.customerRepository = customerRepository;
    this.customerMapper = new CustomerMapper();
  }

  /**
   * 회원가입시 로직
   * NOTE: 이후 return value 조정이 필요함.
   *
   * @param customerReqDto
   * @return Customer
   * @throws BadRequestException
   */
  @Transactional
  public Customer signUp(CustomerReqDto customerReqDto) throws BadRequestException {
    final String nickname = customerReqDto.getNickname();
    final String email = customerReqDto.getEmail();

    if (customerQueryService.isDuplicateNickname(nickname)) {
      throw new BadRequestException("Duplicated nickname");
    }
    if (customerQueryService.isDuplicateEmail(email)) {
      throw new BadRequestException("Duplicated email");
    }
    return customerRepository.save(customerReqDto.toEntity());
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
  public Customer update(Long id, CustomerReqDto customerReqDto) throws BadRequestException {
    final Customer customer = customerRepository.findById(id)
                                                .orElseThrow(() -> new BadRequestException(
                                                    "Not exist customer id"));
    final String updateNickname = customerReqDto.getNickname();
    final String updateEmail = customerReqDto.getEmail();

    if (customerQueryService.isDuplicateNickname(id, updateNickname)) {
      throw new BadRequestException("Duplicated nickname");
    }
    if (customerQueryService.isDuplicateEmail(id, updateEmail)) {
      throw new BadRequestException("Duplicated email");
    }
    customer.setNickname(updateNickname);
    customer.setEmail(updateEmail);
    return customerRepository.save(customer);
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
}
