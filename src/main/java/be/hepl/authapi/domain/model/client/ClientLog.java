package be.hepl.authapi.domain.model.client;


import java.time.Instant;

public class ClientLog {
    private String id;

    private String clientId;

    private Instant timestamp;

    private ClientLoginMethod method;

    private Boolean success;

    private String email;

    private String phoneNumber;

    private String challengeSend;

    private Instant challengeSendTimestamp;

    private String challengeReceive;

    private Instant challengeReceiveTimestamp;

    private String firstName;

    private String lastName;

    private Gender gender;

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

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
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

    public Instant getChallengeSendTimestamp() {
        return challengeSendTimestamp;
    }

    public void setChallengeSendTimestamp(Instant challengeSendTimestamp) {
        this.challengeSendTimestamp = challengeSendTimestamp;
    }

    public String getChallengeReceive() {
        return challengeReceive;
    }

    public void setChallengeReceive(String challengeReceive) {
        this.challengeReceive = challengeReceive;
    }

    public Instant getChallengeReceiveTimestamp() {
        return challengeReceiveTimestamp;
    }

    public void setChallengeReceiveTimestamp(Instant challengeReceiveTimestamp) {
        this.challengeReceiveTimestamp = challengeReceiveTimestamp;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}