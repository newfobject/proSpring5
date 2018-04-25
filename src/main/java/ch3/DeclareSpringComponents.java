package ch3;

import ch2.MessageProvider;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DeclareSpringComponents {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:spring/app-context-annotated.xml");
        context.refresh();
        MessageProvider provider = context.getBean("provider", MessageProvider.class);
        System.out.println(provider.getMessage());
    }
}
