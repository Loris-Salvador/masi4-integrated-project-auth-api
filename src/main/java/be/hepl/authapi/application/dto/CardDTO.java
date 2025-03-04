package be.hepl.authapi.application.dto;

public class CardDTO {
    private String cardNumber;

    public CardDTO(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardDTO() {}

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
