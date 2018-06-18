package ch5;

import ch2.common.Guitar;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnnotationAdvice {

    @Pointcut("execution(* ch5..sing*(ch2.common.Guitar)) && args(value)")
    public void singExecution(Guitar value) {

    }

    //    @Pointcut("bean(john*)")
    @Pointcut("")
    public void isJohn() {
    }

    @Before("singExecution(value) && isJohn()")
    public void simpleBeforeAdvice(JoinPoint joinPoint, Guitar value) {
        if ("Gibson".equals(value.getBrand())) {
            System.out.println("Executing: "
                    + joinPoint.getSignature().getDeclaringTypeName() + " "
                    + joinPoint.getSignature().getName() + " argument: " + value.getBrand());
        }
    }

    @Around("singExecution(value) && isJohn()")
    public Object simpleAroundAdvice(ProceedingJoinPoint pfp, Guitar value) throws Throwable {
        System.out.println("Before execution: " +
                pfp.getSignature().getDeclaringTypeName() + " " +
                pfp.getSignature().getName() + " argument: " + value.getBrand());

        Object retValue = pfp.proceed();
        System.out.println("After execution: " + pfp.getSignature().getDeclaringTypeName()
                + " " + pfp.getSignature().getName() + " argument: " + value.getBrand());
        return retValue;
    }
}
