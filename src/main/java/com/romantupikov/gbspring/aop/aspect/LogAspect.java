package com.romantupikov.gbspring.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

//    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
//
//    @Pointcut("within(@org.springframework.stereotype.Service *)")
//    public void serviceClassMethods() {}
//
//    // Кидает логи затраченного времени на выполнение методов класса, помеченного аннотацией @Service
//    @Around("serviceClassMethods()")
//    public Object logServiceMethodsExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        long start = System.currentTimeMillis();
//
//        Object proceed = joinPoint.proceed();
//
//        long executionTime = System.currentTimeMillis() - start;
//
//        log.info("{} executed in {}ms", joinPoint.getSignature().toShortString(), executionTime);
//
//        return proceed;
//    }
//
//    // Логгирует перед вызовом метода, помеченного кастомной аннотацией (@LogBefore)
//    @Before("@annotation(com.romantupikov.gbspring.aop.annotation.LogBefore)")
//    public void logBefore(JoinPoint joinPoint) throws Throwable {
//        log.info("{}", joinPoint);
//    }

}
