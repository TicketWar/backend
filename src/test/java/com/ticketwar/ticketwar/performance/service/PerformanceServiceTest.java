package com.ticketwar.ticketwar.performance.service;

import com.ticketwar.ticketwar.performance.dto.AddPerformanceRequest;
import com.ticketwar.ticketwar.performance.entity.Performance;
import com.ticketwar.ticketwar.performance.repository.PerformanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PerformanceServiceTest {

  @Mock private PerformanceRepository performanceRepository;

  @InjectMocks private PerformanceService performanceService;

  private Performance performance;

  @BeforeEach
  public void setup() {
    performance =
        Performance.builder()
            .name("아이유 H.E.R")
            .ticketingStartAt(
                Year.of(2002).atMonth(6).atDay(18).atTime(20, 30).atZone(ZoneId.of("Asia/Seoul")))
            .ticketingEndAt(
                Year.of(2002).atMonth(6).atDay(18).atTime(20, 30).atZone(ZoneId.of("Asia/Seoul")))
            .build();
  }

  @Test
  @DisplayName("Performance Service save test")
  public void givenPerformanceObject_whenSavePerformance_thenReturnPerformanceObject() {
    // given - precondition or setup
    // lenient().given(performanceRepository.findById(99L)).willReturn(Optional.empty());
    //mockito 엄격성으로 인한 오류? 공부 필요

//  any 로 바꾸니깐 테스트 통과 왜죠...
//    given 으로 입력값과 출력의 예시를 정해줍니다. 이후 실행결과를 확인합니다. Repository 는 Mock 이기에 실제 작동이 불가능합니다.
//    given(performanceRepository.save(performance)).willReturn(performance);
    given(performanceRepository.save(any(Performance.class))).willReturn(performance);

    System.out.println(performanceRepository);

    System.out.println(performanceService);

    // when - action or the behaviour that we are going test
    Performance savedPerformance =
        performanceService.save(
            new AddPerformanceRequest(
                performance.getName(),
                performance.getTicketingStartAt(),
                performance.getTicketingEndAt()));

    System.out.println(savedPerformance);

    // then - verify the output
    assertThat(savedPerformance).isNotNull();
  }
}
