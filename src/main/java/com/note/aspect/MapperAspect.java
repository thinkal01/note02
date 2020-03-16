package com.note.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MapperAspect {

    @Pointcut("execution(* com.note.mapper.*Mapper.*(..))")
    private void pointCutMethod() {
    }

    @Around("pointCutMethod()")
    public Object doMapperTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();

        log.info("调用Mapper方法：{}，耗时：{}毫秒", proceedingJoinPoint.getSignature(), end - start);
        return obj;
    }
}
