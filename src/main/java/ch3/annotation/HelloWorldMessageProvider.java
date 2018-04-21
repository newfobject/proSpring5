package ch3.annotation;

import ch2.MessageProvider;
import org.springframework.stereotype.Component;

//simple bean
@Component("provider")
public class HelloWorldMessageProvider implements MessageProvider {

    public String getMessage() {
        return "Hello World";
    }
}
