package com.epam.spring.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Map;

/**
 * Created by Olga Kramska on 24-Sep-16.
 */
@Aspect
public class StatisticAspect {
    private Map<Class<?>, Integer> counter;

    @Pointcut("execution(* *logEvent(..))")
    private void allLogEventMethods() {
    }

    @AfterReturning(pointcut = "allLogEventMethods()")
    public void count(JoinPoint joinPoint) {
        Class<?> loggerClass = joinPoint.getTarget().getClass();
        if (counter == null) return;
        if (!counter.containsKey(loggerClass)) {
            counter.put(loggerClass, 1);
        } else {
            counter.put(loggerClass, counter.get(loggerClass) + 1);
        }
    }

    public Map<Class<?>, Integer> getCounter() {
        return counter;
    }
}
