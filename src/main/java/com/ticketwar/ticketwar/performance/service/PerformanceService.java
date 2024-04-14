package com.ticketwar.ticketwar.performance.service;

import com.ticketwar.ticketwar.performance.repository.PerformanceRepository;
import org.springframework.stereotype.Service;

@Service
public class PerformanceService {

  private final PerformanceRepository performanceRepository;

  public PerformanceService(PerformanceRepository performanceRepository) {
    this.performanceRepository = performanceRepository;
  }
}
