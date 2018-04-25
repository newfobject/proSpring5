package ch3.annotated;

import ch2.MessageProvider;

//simple bean
//@Component("provider")
public class HelloWorldMessageProvider implements MessageProvider {

    public String getMessage() {
        return "Hello World";
    }
}
