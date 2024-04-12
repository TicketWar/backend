package com.ticketwar.ticketwar.performance.entity;

import com.ticketwar.ticketwar.common.entity.trackable.CreatedAndUpdatedTimeTrackable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "PERFORMANCE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Performance extends CreatedAndUpdatedTimeTrackable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "performance_id")
  private Long id;

  @Column(name = "name", length = 100, nullable = false)
  private String name;

  @Column(name = "start_at", nullable = false)
  private ZonedDateTime ticketingStartAt;

  @Column(name = "end_at", nullable = false)
  private ZonedDateTime ticketingEndAt;

  @Builder
  protected Performance(
      @NonNull String name,
      @NonNull ZonedDateTime ticketingStartAt,
      @NonNull ZonedDateTime ticketingEndAt) {
    this.name = name;
    this.ticketingStartAt = ticketingStartAt;
    this.ticketingEndAt = ticketingEndAt;
  }
}
