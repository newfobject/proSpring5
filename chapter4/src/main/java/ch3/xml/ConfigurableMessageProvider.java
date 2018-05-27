package ch3.xml;

import ch2.MessageProvider;


public class ConfigurableMessageProvider implements MessageProvider {
    private String message;

    public ConfigurableMessageProvider() {
    }

    public ConfigurableMessageProvider(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
