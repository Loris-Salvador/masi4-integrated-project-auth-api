package be.hepl.authapi.domain.model.client;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

public class ClientLog {
    private String id;

    private String clientId;

    private Long timestamp;

    private ClientLoginMethod method;

    private Boolean success;

    private String email;

    private String phoneNumber;

    private String challengeSend;

    private Long challengeSendTimestamp;

    private String challengeReceive;

    private Long challengeReceiveTimestamp;

    private String creditCard;

    private String nationalId;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    private String gender;

    public ClientLog() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public ClientLoginMethod getMethod() {
        return method;
    }

    public void setMethod(ClientLoginMethod method) {
        this.method = method;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getChallengeSend() {
        return challengeSend;
    }

    public void setChallengeSend(String challengeSend) {
        this.challengeSend = challengeSend;
    }

    public Long getChallengeSendTimestamp() {
        return challengeSendTimestamp;
    }

    public void setChallengeSendTimestamp(Long challengeSendTimestamp) {
        this.challengeSendTimestamp = challengeSendTimestamp;
    }

    public String getChallengeReceive() {
        return challengeReceive;
    }

    public void setChallengeReceive(String challengeReceive) {
        this.challengeReceive = challengeReceive;
    }

    public Long getChallengeReceiveTimestamp() {
        return challengeReceiveTimestamp;
    }

    public void setChallengeReceiveTimestamp(Long challengeReceiveTimestamp) {
        this.challengeReceiveTimestamp = challengeReceiveTimestamp;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
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
}