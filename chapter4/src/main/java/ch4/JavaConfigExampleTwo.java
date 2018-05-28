package ch4;

import ch2.MessageRenderer;
import ch4.multiple.AppConfigThree;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigExampleTwo {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigThree.class);
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigFive.class);
        MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        renderer.render();
    }
}
