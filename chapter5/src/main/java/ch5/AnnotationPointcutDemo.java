package ch5;

import ch2.common.Guitar;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class AnnotationPointcutDemo {
    public static void main(String[] args) {
        Guitarist johnMayer = new Guitarist();

        AnnotationMatchingPointcut pc = AnnotationMatchingPointcut
                .forMethodAnnotation(AdviceRequired.class);
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(johnMayer);
        proxyFactory.addAdvisor(advisor);
        Guitarist proxy = (Guitarist) proxyFactory.getProxy();

        proxy.sing(new Guitar());
        proxy.rest();
    }
}
