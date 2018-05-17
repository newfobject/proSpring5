package ch3.config;

import ch3.sandbox.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

public class TargetDemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(TargetConfig.class);
        TrickyTarget t = ctx.getBean(TrickyTarget.class);
        ctx.close();
    }

    @Configuration
    static class TargetConfig {

        @Bean
        public Foo fooImplOne() {
            return new FooImplOne();
        }

        @Bean
        public Foo fooImplTwo() {
            return new FooImplTwo();
        }

        @Bean
        public Bar bar() {
            return new Bar();
        }

        @Bean
        public TrickyTarget trickyTarget() {
            return new TrickyTarget();
        }
    }
}
