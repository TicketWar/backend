package com.ticketwar.ticketwar.repository;

import com.ticketwar.ticketwar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
