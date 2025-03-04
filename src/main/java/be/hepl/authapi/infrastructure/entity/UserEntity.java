package be.hepl.authapi.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;
    private String email;
    private String cardNumber;

    public UserEntity() {}

    public UserEntity(String cardNumber, String email) {
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
