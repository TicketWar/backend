package com.ticketwar.ticketwar.performance.dto;

import com.ticketwar.ticketwar.performance.entity.Performance;

import java.time.ZonedDateTime;

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
