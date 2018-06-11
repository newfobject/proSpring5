package ch5;

import org.springframework.aop.framework.ProxyFactory;

public class AfterAdviceDemo {
    public static KeyGenerator getKeyGenerator() {
        KeyGenerator target = new KeyGenerator();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvice(new WeakKeyCheckAdvice());
        return (KeyGenerator) pf.getProxy();
    }

    public static void main(String[] args) {
        KeyGenerator keyGen = getKeyGenerator();

        for (int i = 0; i < 10; i++) {
            try {
                long key = keyGen.getKey();
                System.out.println("Key: " + key);
            } catch (SecurityException e) {
                System.out.println("Weak Key Generated!");
            }
        }
    }
}
