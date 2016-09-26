package com.epam.spring.aspects;

import com.epam.spring.beans.Event;
import com.epam.spring.loggers.EventLogger;
import com.epam.spring.loggers.FileEventLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.IOException;

/**
 * Created by Olga_Kramska on 9/1/2016.
 */
@Aspect
public class LoggingAspect {
    private StatisticAspect statisticAspect;
    private EventLogger otherLogger;

    public LoggingAspect(StatisticAspect statisticAspect, EventLogger otherLogger) {
        this.statisticAspect = statisticAspect;
        this.otherLogger = otherLogger;
    }

    @Pointcut("execution(* *logEvent(..))")
    private void allLogEventMethods() {
    }

    @Pointcut("execution(* *File*Logger.init())")
    private void initMethodInFileLogger(){}

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("BEFORE: " + joinPoint.getTarget().getClass().getSimpleName() + " " +
                joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "initMethodInFileLogger()", throwing = "ex")
    public void logAfterThrown(IOException ex){
        System.out.println("Can't write to file");
    }

    @Around("execution(* *ConsoleLogger.logEvent(..)) && args(event)")
    public void aroundConsoleLogging(ProceedingJoinPoint joinPoint, Event event) throws Throwable {
        int count = statisticAspect.getCounter().get(joinPoint.getTarget().getClass());
        if(count < 10){
            joinPoint.proceed(new Object[]{event});
        } else {
           otherLogger.logEvent(event);
        }
    }
}
