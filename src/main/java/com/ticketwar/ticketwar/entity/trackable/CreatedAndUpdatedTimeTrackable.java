package com.ticketwar.ticketwar.entity.trackable;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
 * Describes an abstract model whose lifecycle is tracked by time.
 * Includes a created field that is set automatically upon object creation, a modified field that is updated automatically upon calling save() on the object whenever a significant change was done, and a cache_version integer field that is automatically incremeneted any time a significant change is done.
 * By a significant change we mean any change outside of those internal created, modified, cache_version, display_count or last_active fields. Full list of ignored fields lies in TimeTrackable.insignificant_fields.
 * Reference : https://pythonhosted.org/lck.django/lck.django.common.models.html
 * */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CreatedAndUpdatedTimeTrackable {
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "update_at")
    private LocalDateTime updatedAt;
}
