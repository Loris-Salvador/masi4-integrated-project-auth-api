package be.hepl.authapi.domain.model;

public class Client {

    private String id;
    private String email;
    private String password;
    private String telephoneNumber;

    public Client() {}

    public Client(String id, String password, String email, String telephoneNumber) {
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