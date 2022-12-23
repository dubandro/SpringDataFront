package ru.geekbrains.dubandro.SpringDataFront.utils;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Data
public class UtilAspect {
    private Long totalDurationProduct = 0L;
    private Long totalDurationCart = 0L;
    private Long totalDurationOrder = 0L;

    @Around("execution(public * ru.geekbrains.dubandro.SpringDataFront.services.ProductService.*(..))")
    public Object profilingProductService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object outObject = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        totalDurationProduct += duration;
        return outObject;
    }

    @Around("execution(public * ru.geekbrains.dubandro.SpringDataFront.services.CartService.*(..))")
    public Object profilingCartService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object outObject = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        totalDurationCart += duration;
        return outObject;
    }

    @Around("execution(public * ru.geekbrains.dubandro.SpringDataFront.services.OrderService.*(..))")
    public Object profilingOrderService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object outObject = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        totalDurationOrder += duration;
        return outObject;
    }
}
