package com.ticketwar.ticketwar.performance.dto;

import com.ticketwar.ticketwar.performance.entity.Performance;
import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;

//jun - test code 작성용 빌더패턴으로 적용할까요?
@AllArgsConstructor
public class AddPerformanceRequest {
    private  String name;
    private ZonedDateTime ticketingStartAt;
    private ZonedDateTime ticketingEndAt;

    public Performance toEntity() {
        return Performance.builder()
                .name(name)
                .ticketingStartAt(ticketingStartAt)
                .ticketingEndAt(ticketingEndAt)
                .build();
    }
}
