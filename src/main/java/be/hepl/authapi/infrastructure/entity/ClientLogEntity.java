package be.hepl.authapi.infrastructure.entity;


import be.hepl.authapi.domain.model.client.ClientLoginMethod;

import be.hepl.authapi.domain.model.client.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

/// <comments>
/// Entity (model pour mapper facilement avec les collections Mongo) pour les clients
/// </comments>
@Document("client_log")
public class ClientLogEntity {
    @Id
    private String id;

    @Field("client_id")
    private String clientId;

    private Instant timestamp;

    private ClientLoginMethod method;

    private Boolean success;

    @Field("challenge_send")
    private String challengeSend;

    @Field("challenge_send_timestamp")
    private Instant challengeSendTimestamp;

    @Field("challenge_receive")
    private String challengeReceive;

    @Field("challenge_receive_timestamp")
    private Instant challengeReceiveTimestamp;

    public ClientLogEntity() {}

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
}