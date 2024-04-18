package com.ticketwar.ticketwar.user.repository;

import com.ticketwar.ticketwar.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * id 를 통해서 customer 의 정보를 조회합니다.
   *
   * @param id must not be {@literal null}.
   * @return Customer
   */
  Optional<User> findById(@Param("id") Long id);

  /**
   * nickname 을 기반으로 customer 의 정보를 조회합니다.
   *
   * @param nickname
   * @return Customer
   */
  Optional<User> findByNickname(@Param("nickname") String nickname);

  /**
   * email 을 기반으로 customer 의 정보를 조회합니다.
   *
   * @param email
   * @return Customer
   */
  Optional<User> findByEmail(@Param("email") String email);
}
