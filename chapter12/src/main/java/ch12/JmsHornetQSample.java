package ch12;

import ch12.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

public class JmsHornetQSample {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        MessageSender messageSender = ctx.getBean("messageSender", MessageSender.class);

        for (int i = 0; i < 10; ++i) {
            messageSender.sendMessage("Test message: " + i);
        }

        System.in.read();
        ctx.close();
    }
}
