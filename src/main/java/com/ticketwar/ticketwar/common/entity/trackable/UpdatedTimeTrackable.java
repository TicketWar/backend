package com.ticketwar.ticketwar.common.entity.trackable;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class UpdatedTimeTrackable {
  @LastModifiedDate
  @Column(name = "update_at", nullable = false)
  private LocalDateTime updatedAt;
}
