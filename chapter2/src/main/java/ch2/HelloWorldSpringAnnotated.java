package ch2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldSpringAnnotated {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        MessageRenderer renderer = context.getBean(MessageRenderer.class);
        renderer.render();
    }
}
