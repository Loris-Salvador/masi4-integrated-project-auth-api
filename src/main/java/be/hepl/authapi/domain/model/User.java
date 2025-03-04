package be.hepl.authapi.domain.model;

public class User {

    private String id;
    private String email;
    private String cardNumber;

    public User() {}

    public User(String cardNumber, String email) {
        this.cardNumber = cardNumber;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String username) {
        this.cardNumber = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}