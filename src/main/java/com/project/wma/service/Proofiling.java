package com.project.wma.service;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.AopInfrastructureBean;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
@NoArgsConstructor
public class Proofiling implements PointcutAdvisor, MethodInterceptor, AopInfrastructureBean {

    private static final Logger logger = Logger.getLogger(Proofiling.class.getName());

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            long elapsedTime = System.currentTimeMillis() - startTime;
            logger.info(() -> String.format("Method %s executed in %d ms",
                    invocation.getMethod().getName(), elapsedTime));
        }
    }

    @Getter
    private final Pointcut pointcut = new AnnotationMatchingPointcut(Timed.class, null, true);

    @Override
    public Advice getAdvice() {
        return null;
    }
}
