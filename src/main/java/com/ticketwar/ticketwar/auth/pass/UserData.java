package com.ticketwar.ticketwar.auth.pass;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * UserData 는 Request 에서 가져올 Attribute 를 지정할 수 있습니다.
 * <p>
 * 기본 값으로 해당 Annotation 은 "UserData" 를 사용합니다.
 *
 * @Warn 반드시 알맞는 타입의 Parameter를 사용해야합니다.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserData {

  public String value() default "USER_DATA";
}
