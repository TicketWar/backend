package com.ticketwar.ticketwar.performance.service;

import com.ticketwar.ticketwar.performance.dto.AddPerformanceRequest;
import com.ticketwar.ticketwar.performance.dto.UpdatePerformanceRequest;
import com.ticketwar.ticketwar.performance.entity.Performance;
import com.ticketwar.ticketwar.performance.exception.PerformanceNotFoundException;
import com.ticketwar.ticketwar.performance.repository.PerformanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerformanceService {
  private final PerformanceRepository performanceRepository;

  public PerformanceService(PerformanceRepository performanceRepository) {
    this.performanceRepository = performanceRepository;
  }

  // 저장
  public Performance save(AddPerformanceRequest request) {
    return performanceRepository.save(request.toEntity());
  }

  // 전체 찾기
  public List<Performance> findAll() {
    return performanceRepository.findAll();
  }

  // id로 찾기
  public Performance findById(Long id) {
    return performanceRepository
        .findById(id)
        .orElseThrow(() -> new PerformanceNotFoundException("not found: " + id));
  }

  // 삭제
  public void delete(Long id) {
    performanceRepository.deleteById(id);
  }

  // 수정
  @Transactional
  public Performance update(Long id, UpdatePerformanceRequest request) {
    Performance performance =
        performanceRepository
            .findById(id)
            .orElseThrow(() -> new PerformanceNotFoundException("not found: " + id));
    performance.update(
        request.getName(), request.getTicketingStartAt(), request.getTicketingEndAt());
    return performance;
  }
}
