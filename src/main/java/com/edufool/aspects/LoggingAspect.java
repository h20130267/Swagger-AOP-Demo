package com.edufool.aspects;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    @Around("within(com.edufool.Delegate..*))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method execution time
        LOGGER.info("Execution time of " + className + "." + methodName + " "
                + ":: " + stopWatch.getTotalTimeMillis() + " ms");

        return result;
    }

    @Before("within(com.edufool.Delegate..*)")
    public void loggingBeforeMethod (JoinPoint joinPoint){
        LOGGER.info("Before Executing the Method " + joinPoint.getSignature().getName() + " of class " + joinPoint.getSignature().getDeclaringType().getSimpleName());
    }

    @After("within(com.edufool.Delegate..*)")
    public void loggingAfterMethodWithPackageBasedParsing (JoinPoint joinPoint){
        LOGGER.info("After Executing the Method " + joinPoint.getSignature().getName() + " of class " + joinPoint.getSignature().getDeclaringType().getSimpleName());
    }
    @After("execution(* com.edufool.Delegate.LibMan.*(..))")
    public void loggingAfterMethod (JoinPoint joinPoint){
        LOGGER.info("After Executing the Method " + joinPoint.getSignature().getName() + " of class " + joinPoint.getSignature().getDeclaringType().getSimpleName());
    }
}
