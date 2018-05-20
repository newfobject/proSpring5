package ch3;

import ch2.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyPull {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring/app-context-xml.xml");
        MessageRenderer renderer = context.getBean("renderer", MessageRenderer.class);
        renderer.render();
    }
}
