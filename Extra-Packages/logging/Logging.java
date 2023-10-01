// package com.inventory.Ingredient.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(org.springframework.stereotype.Component.*)" +
            " || within(org.springframework.web.bind.annotation.RestController.*)")
    public void springBeanPointcut() {
    }

    @Pointcut("execution(* com.hackathon.*.*.*.*(..))")
    public void applicationPackagePointcut() {
    }

    @Around("springBeanPointcut() || applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed;
        log.info("Entering : {}\nArguments : {}", joinPoint.getSignature().toShortString(), Arrays.toString(joinPoint.getArgs()));
        try{
            proceed = joinPoint.proceed();
        } catch (IllegalArgumentException e){
            log.error("Illegal argument(s): {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        } finally{
            log.info("Exiting : {}\nReturning : {}", joinPoint.getSignature().toShortString(), joinPoint.getSignature().toShortString());
        }
        return proceed;
    }

    @AfterThrowing(pointcut = "springBeanPointcut() || applicationPackagePointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception thrown in: {}\nException: {}\nMessage: {}", joinPoint.getSignature().toShortString(), e.getClass(), e.getMessage());
    }

}
