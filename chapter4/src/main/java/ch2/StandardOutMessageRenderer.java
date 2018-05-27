package ch2;

public class StandardOutMessageRenderer implements MessageRenderer {

    private MessageProvider messageProvider;

    public StandardOutMessageRenderer() {
        System.out.println(this.getClass().getSimpleName() + ": constructor called");
    }

    @Override
    public void render() {
        if (messageProvider == null)
            throw new RuntimeException(
                    "You must set the property messageProvider of class" +
                            this.getClass().getName());
        System.out.println(messageProvider.getMessage());
    }

    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }

    @Override
    public void setMessageProvider(MessageProvider provider) {
        System.out.println("Setting message provider");
        this.messageProvider = provider;
    }
}
