package com.ticketwar.ticketwar.service;

import com.ticketwar.ticketwar.entity.Performance;
import com.ticketwar.ticketwar.repository.PerformanceRepository;
import org.springframework.stereotype.Service;

@Service
public class PerformanceService {
    private final PerformanceRepository performanceRepository;

    public PerformanceService(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }
}
