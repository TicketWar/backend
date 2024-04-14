package com.ticketwar.ticketwar.performance.repository;

import com.ticketwar.ticketwar.performance.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

}
