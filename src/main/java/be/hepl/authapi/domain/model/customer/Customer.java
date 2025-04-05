package be.hepl.authapi.domain.model.customer;


import java.time.Instant;

public class Customer {

    private String id;

    private String email;

    private String password;

    private String phoneNumber;

    private String firstName;

    private String name;

    private Gender gender;

    private boolean emailVerified;

    private boolean phoneVerified;

    private Instant birthday;

    private Instant createAccount;


    public Customer(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public String getLastName() {
        return name;
    }

    public void setLastName(String lastName) {
        this.name = lastName;
    }

    public Instant getBirthDate() {
        return birthday;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthday = birthDate;
    }

    public Instant getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(Instant createAccount) {
        this.createAccount = createAccount;
    }
}