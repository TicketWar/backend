package com.ticketwar.ticketwar.common.interfaces;

/**
 * DTO 대상으로 사용하는 Interface. Entity 로 변환 가능한 경우 implement 하여 사용한다. 일반적으로 RequestDTO 에서 사용함.
 *
 * @param <T> 변환 대상 엔티티
 */
public interface EntityConvertable<T> {

  T toEntity();
}