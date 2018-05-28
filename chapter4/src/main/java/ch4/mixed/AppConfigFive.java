package ch4.mixed;


import ch2.MessageProvider;
import ch2.MessageRenderer;
import ch2.StandardOutMessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:spring/app-context-xml-01.xml")
public class AppConfigFive {

    @Autowired
    MessageProvider messageProvider;

    @Bean(name = "messageRenderer")
    public MessageRenderer messageRenderer() {
        MessageRenderer messageRenderer = new StandardOutMessageRenderer();
        messageRenderer.setMessageProvider(messageProvider);
        return messageRenderer;
    }
}
