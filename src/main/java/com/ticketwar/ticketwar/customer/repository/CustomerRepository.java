package com.ticketwar.ticketwar.customer.repository;

import com.ticketwar.ticketwar.customer.entity.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  /**
   * id 를 통해서 customer 의 정보를 조회합니다.
   *
   * @param id must not be {@literal null}.
   * @return Customer
   */
  Optional<Customer> findById(@Param("id") Long id);

  /**
   * nickname 을 기반으로 customer 의 정보를 조회합니다.
   *
   * @param nickname
   * @return Customer
   */
  Optional<Customer> findByNickname(@Param("nickname") String nickname);

  /**
   * email 을 기반으로 customer 의 정보를 조회합니다.
   *
   * @param email
   * @return Customer
   */
  Optional<Customer> findByEmail(@Param("email") String email);

  /**
   * 이미 존재하는 nickname 이 있는지 확인함.
   *
   * @param nickname
   * @return 존재할 경우 true
   */
  @Query("""
      SELECT COUNT(c) > 0 FROM Customer c
      WHERE c.nickname = :nickname
      """)
  boolean isDuplicateNickname(@Param("nickname") String nickname);

  /**
   * 이미 존재하는 email 이 있는지 확인함.
   *
   * @param email
   * @return 존재할 경우 true
   */
  @Query("""
      SELECT COUNT(c) > 0 FROM Customer c
      WHERE c.email = :email
      """)
  boolean isDuplicateEmail(@Param("email") String email);
}
