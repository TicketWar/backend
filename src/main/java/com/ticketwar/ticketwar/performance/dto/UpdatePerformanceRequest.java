package com.ticketwar.ticketwar.performance.dto;

import com.ticketwar.ticketwar.performance.entity.Performance;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class UpdatePerformanceRequest {
    private String name;
    private ZonedDateTime ticketingStartAt;
    private ZonedDateTime ticketingEndAt;
}
