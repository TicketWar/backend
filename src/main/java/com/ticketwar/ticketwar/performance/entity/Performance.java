package com.ticketwar.ticketwar.performance.entity;

import com.ticketwar.ticketwar.common.entity.trackable.CreatedAndUpdatedTimeTrackable;
import jakarta.persistence.*;
import java.time.ZonedDateTime;
import lombok.*;

@Entity(name = "PERFORMANCE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Performance extends CreatedAndUpdatedTimeTrackable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "performance_id")
  private Long id;

  @Column(name = "name", length = 100, nullable = false)
  private @NonNull String name;

  @Column(name = "start_at", nullable = false)
  private @NonNull ZonedDateTime ticketingStartAt;

  @Column(name = "end_at", nullable = false)
  private @NonNull ZonedDateTime ticketingEndAt;

  @Builder
  protected Performance(
      @NonNull String name,
      @NonNull ZonedDateTime ticketingStartAt,
      @NonNull ZonedDateTime ticketingEndAt) {
    this.name = name;
    this.ticketingStartAt = ticketingStartAt;
    this.ticketingEndAt = ticketingEndAt;
  }

  public void update(String name, ZonedDateTime ticketingStartAt, ZonedDateTime ticketingEndAt) {
    this.name = name;
    this.ticketingStartAt = ticketingStartAt;
    this.ticketingEndAt = ticketingEndAt;
  }
}
