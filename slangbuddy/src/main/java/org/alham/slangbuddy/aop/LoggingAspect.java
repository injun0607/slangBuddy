package org.alham.slangbuddy.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 남겨야하는 로그
 * 시작시간
 * 종료시간
 * 걸린시간
 *
 * 연결된 세션정보
 * 요청된 아이피
 * 요청한 url
 * jwtToken의 userId
 * 성공여부
 */

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* org.alham.slangbuddy.service..*.*(..))")
    public Object handleTodoServiceException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 메서드 실행
            log.debug("ServiceExceptionAspect handleTodoServiceException");
            String className = joinPoint.getSignature().getDeclaringType().getName();
            String methodName = joinPoint.getSignature().getName();
            log.info("className = {},mehodName = {}", className, methodName);

            return joinPoint.proceed();
        } catch (Exception ex) {
            // 커스텀 예외로 변환
            throw new Exception(ex.getMessage());
        }
    }



}
