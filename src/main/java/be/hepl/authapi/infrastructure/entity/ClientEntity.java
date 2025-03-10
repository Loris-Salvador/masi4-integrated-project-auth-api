package be.hepl.authapi.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
public class ClientEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String password;

    private String telephoneNumber;

    public ClientEntity() {}

    public ClientEntity(String id, String password, String email, String telephoneNumber) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
