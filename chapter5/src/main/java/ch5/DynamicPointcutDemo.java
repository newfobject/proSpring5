package ch5;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class DynamicPointcutDemo {
    public static void main(String[] args) {
        SampleBean sampleBean = new SampleBean();

        Advisor advisor = new DefaultPointcutAdvisor(
                new SimpleDynamicPointcut(), new SimpleAdvice()
        );

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(sampleBean);

        SampleBean proxy = (SampleBean) proxyFactory.getProxy();

        proxy.foo(1);
        proxy.foo(10);
        proxy.foo(100);

        proxy.bar();
        proxy.bar();
        proxy.bar();
    }
}
