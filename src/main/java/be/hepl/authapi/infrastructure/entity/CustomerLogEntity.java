package be.hepl.authapi.infrastructure.entity;


import be.hepl.authapi.domain.model.customer.CustomerLoginMethod;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document("customer_logs")
public class CustomerLogEntity {
    @Id
    private String id;

    @Field("customer_id")
    private String customerId;

    private Instant timestamp;

    private CustomerLoginMethod method;

    private Boolean success;

    @Field("challenge_send")
    private String challengeSend;

    @Field("challenge_send_timestamp")
    private Instant challengeSendTimestamp;

    @Field("challenge_receive")
    private String challengeReceive;

    @Field("challenge_receive_timestamp")
    private Instant challengeReceiveTimestamp;

    public CustomerLogEntity() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public CustomerLoginMethod getMethod() {
        return method;
    }

    public void setMethod(CustomerLoginMethod method) {
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}