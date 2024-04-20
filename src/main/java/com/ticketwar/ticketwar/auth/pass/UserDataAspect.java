package com.ticketwar.ticketwar.auth.pass;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component
public class UserDataAspect {

  /**
   * TokenData annotation 을 적용한 Parameter 에 대해서 Request의 TokenData Attribute 를 가져옵니다. Pointcut :
   * method(params.., @TokenData(Type data), params..)
   * <p>
   * TokenData 의 경우 v
   *
   * @param joinPoint
   * @return
   * @throws Throwable
   */
  @Around("execution(* *(.., @com.ticketwar.ticketwar.auth.pass.UserData (*), ..))")
  public Object handleUserData(ProceedingJoinPoint joinPoint)
      throws Throwable {
    final Signature signature = joinPoint.getSignature();
    final MethodSignature methodSignature = (MethodSignature) signature;
    final Method method = methodSignature.getMethod();
    final Parameter[] parameters = method.getParameters();
    final Object args[] = joinPoint.getArgs();
    final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
        .currentRequestAttributes()).getRequest();

    for (int idx = 0; idx < parameters.length; ++idx) {
      UserData userData = parameters[idx].getAnnotation(UserData.class);
      if (userData != null) {
        args[idx] = request.getAttribute(userData.value());
        break;
      }
    }
    return joinPoint.proceed(args);
  }
}
