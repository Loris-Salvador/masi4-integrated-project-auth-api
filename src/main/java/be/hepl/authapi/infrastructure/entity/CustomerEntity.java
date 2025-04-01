package be.hepl.authapi.infrastructure.entity;

import be.hepl.authapi.domain.model.customer.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

/// <comments>
/// Entity (model pour mapper facilement avec les collections Mongo) pour les clients
/// </comments>
@Document(collection = "customers")
public class CustomerEntity {
    @Id
    private String id;

    @Field("create_account")
    private Instant createAccount;

    private String name;

    @Field("first_name")
    private String firstName;

    private String email;

    @Field("email_verified")
    private boolean emailVerified;

    private String password;

    @Field("phone")
    private String phoneNumber;

    @Field("phone_verified")
    private boolean phoneVerified;

    private Gender gender;

    private Instant  birthday;


    public CustomerEntity() {}

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

    public String getLastName() {
        return name;
    }

    public void setLastName(String lastName) {
        this.name = lastName;
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
