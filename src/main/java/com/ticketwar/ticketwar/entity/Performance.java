package com.ticketwar.ticketwar.entity;

import java.time.ZonedDateTime;

import com.ticketwar.ticketwar.entity.trackable.CreatedAndUpdatedTimeTrackable;
import jakarta.persistence.*;
import lombok.*;

/*
*
* (1) Why Java `Date` class is not recommended?
* https://d2.naver.com/helloworld/645609
*
* (2) What is "@MappedSuperClass" ?
* https://offetuoso.github.io/blog/develop/backend/orm-jpa-basic/mapped-superclass/
* @Entity 는 @Entity 혹은 @MappedSuperClass 만 상속 가능.
*
* (2) Time zone problem in DB and JPA
* https://stackoverflow.com/questions/78144490/time-changed-in-mysql-when-i-use-jpa-save-the-entity
*
* (3) ZonedDateTime vs LocalDateTime
* https://stackoverflow.com/questions/62048952/how-can-we-decide-whether-to-use-zoneddatetime-or-localdatetime
*
* (2) JPA Auditing
* https://velog.io/@kimdoha/JPA-LocalDateTime-VS-ZonedDateTime-Auditing
* https://taegyunwoo.github.io/jpa/JPA_AutoCreatedModifiedTime
*
* (3) MySQL time stamp
* https://velog.io/@mintzzz1009/MySQL-TIL-MySQL%EC%97%90%EC%84%9C-createdAt%EA%B3%BC-updatedAt-%EC%84%A4%EC%A0%95
*
* */

@Entity
@Getter @Setter
@EqualsAndHashCode(callSuper = true) @ToString
@RequiredArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PERFORMANCE")
public class Performance extends CreatedAndUpdatedTimeTrackable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id")
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(100)")
    private @NonNull String name;

    @Column(name = "start_at")
    private @NonNull ZonedDateTime ticketingStartAt;

    @Column(name = "end_at")
    private @NonNull ZonedDateTime ticketingEndAt;
}
