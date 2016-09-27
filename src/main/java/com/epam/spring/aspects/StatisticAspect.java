package com.epam.spring.aspects;

import com.epam.spring.loggers.CombinedEventLogger;
import com.epam.spring.loggers.ConsoleEventLogger;
import com.epam.spring.loggers.FileEventLogger;
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

    public StatisticAspect(Map<Class<?>, Integer> counter) {
        this.counter = counter;
        counter.put(CombinedEventLogger.class, 0);
        counter.put(FileEventLogger.class, 0);
        counter.put(ConsoleEventLogger.class, 0);
    }

    @Pointcut("execution(* com.epam.spring.loggers.*.logEvent(..))")
    private void allLogEventMethods() {
    }


    @AfterReturning(pointcut = "allLogEventMethods()")
    public void count(JoinPoint joinPoint) {
        Class<?> loggerClass = joinPoint.getTarget().getClass();
//        if (!counter.containsKey(loggerClass)) {
//            counter.put(loggerClass, 1);
//        } else {
            counter.put(loggerClass, counter.get(loggerClass) + 1);
//        }
    }

    public Map<Class<?>, Integer> getCounter() {
        return counter;
    }
}
