package com.example.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TonjiTimeAop {

    @Around("execution(* com.example.co ntroller.*.*(..))")
    public Object yeWuTime (ProceedingJoinPoint pjp) throws Throwable {
        log.info("开始计时");
        long kaishi = System.currentTimeMillis();

        Object reult = pjp.proceed();

        long jieshu = System.currentTimeMillis();
        log.info("业务 {} 执行结束，耗时：{}ms",pjp.getSignature(),jieshu-kaishi);
        return reult;

    }

}
