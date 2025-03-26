package be.hepl.authapi.infrastructure.entity;

import be.hepl.authapi.domain.model.client.ClientLoginMethod;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;


@Document(collection = "clients")
public class ClientEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String password;

    @Indexed(unique = true)
    @Field("phone_number")
    private String phoneNumber;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    private String gender;

    @Field("email_verified")
    private boolean emailVerified;

    @Field("phone_verified")
    private boolean phoneVerified;

    @Field("masi_id_verified")
    private boolean masiIdVerified;

    @Field("birth_date")
    private Long birthDate;

    @Field("create_account")
    private Long createAccount;

    public ClientEntity() {}

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
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }

    public Long getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(Long createAccount) {
        this.createAccount = createAccount;
    }

    public boolean isMasiIdVerified() {
        return masiIdVerified;
    }

    public void setMasiIdVerified(boolean masiIdVerified) {
        this.masiIdVerified = masiIdVerified;
    }
}
