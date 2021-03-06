package ch2;

public class StandardOutMessageRenderer implements MessageRenderer {

    private MessageProvider messageProvider;

    public void render() {
        if (messageProvider == null)
            throw new RuntimeException(
                    "You must set the property messageProvider of class" +
                            this.getClass().getName());
        System.out.println(messageProvider.getMessage());
    }

    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }

    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }
}
