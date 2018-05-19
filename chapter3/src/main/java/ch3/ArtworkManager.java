package ch3;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArtworkManager {
    private List<ArtworkSender> artworkSenders = new ArrayList<>();
    private String artWorkPath;
    private Recipient recipient;
    private String deliveryOption;

    public void setArtworkSenders(List<ArtworkSender> artworkSenders) {
        this.artworkSenders.addAll(artworkSenders);
    }

    public void setArtWorkPath(String artWorkPath) {
        this.artWorkPath = artWorkPath;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public void setDeliveryOption(String deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    /**
     * Sends the artwork
     *
     * @return <code>true</code> if the artwork has been sent,
     * otherwise <code>false</code>
     */
    public boolean sendArtwork() {
        validateFields();
        Optional<ArtworkSender> senderOptional = artworkSenders.stream()
                .filter(o -> deliveryOption.equals(o.getFriendlyName()))
                .findFirst();
        if (!senderOptional.isPresent()) return false;
        else
            senderOptional.get().sendArtwork(artWorkPath, recipient);
        return true;
    }

    public List<String> deliveryOptions() {
        return artworkSenders.stream()
                .map(ArtworkSender::getFriendlyName)
                .collect(Collectors.toList());
    }

    private void validateFields() {
        if (StringUtils.isEmpty(artWorkPath))
            throw new IllegalArgumentException("artworkPath is not correct");
        if (StringUtils.isEmpty(deliveryOption))
            throw new IllegalArgumentException("Delivery option is not correct");
        if (CollectionUtils.isEmpty(artworkSenders))
            throw new IllegalArgumentException("Artwork senders are not set");
    }
}
