package ch4.config;

import ch4.DestructiveBeanWithJSR250;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import static ch4.config.DestructiveBeanConfigDemo.DestructiveBeanConfig;

public class DestructiveBeanWithHook {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(
                DestructiveBeanConfig.class);
        ctx.getBean(DestructiveBeanWithJSR250.class);
        ctx.registerShutdownHook();
    }
}
