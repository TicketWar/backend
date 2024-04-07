package com.ticketwar.ticketwar.repository;

import com.ticketwar.ticketwar.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}
