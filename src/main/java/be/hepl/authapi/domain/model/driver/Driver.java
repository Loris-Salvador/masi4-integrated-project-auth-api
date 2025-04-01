package be.hepl.authapi.domain.model.driver;

import be.hepl.authapi.domain.model.customer.Gender;

import java.time.Instant;

public class Driver {

    private String phoneNumber;

    private Instant createAccount;

    private String name;

    private String firstName;

    private Gender gender;

    private Instant birthday;

    public Driver() {}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(Instant createAccount) {
        this.createAccount = createAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Instant getBirthday() {
        return birthday;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }
}
