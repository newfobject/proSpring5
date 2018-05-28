package ch4.mixed;

import ch2.MessageProvider;
import ch3.xml.ConfigurableMessageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigSix {

    @Bean(name = "provider")
    public MessageProvider messageProvider() {
        return new ConfigurableMessageProvider("Love on the weekend");
    }
}
