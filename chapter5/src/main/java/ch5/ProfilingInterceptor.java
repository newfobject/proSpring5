package ch5;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

public class ProfilingInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(invocation.getMethod().getName());

        Object returnValue = invocation.proceed();
        stopWatch.stop();
        dumInfo(invocation, stopWatch.getTotalTimeMillis());
        return returnValue;
    }

    private void dumInfo(MethodInvocation invocation, long totalTimeMillis) {
        Method m = invocation.getMethod();
        Object target = invocation.getThis();
        Object[] args = invocation.getArguments();

        System.out.println("Executed method: " + m.getName());
        System.out.println("On object of type: " + target.getClass().getName());

        System.out.println("With arguments:");
        for (Object arg : args) {
            System.out.print("        > " + arg);
        }
        System.out.println("\n");

        System.out.println("Took: " + totalTimeMillis + " ms");
    }
}
