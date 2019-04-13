package com.voteva.remittance.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TransactionDto {

    private String id;
    private String senderId;
    private String recipientId;
    private String amount;
    private Date date;

    @JsonCreator
    public TransactionDto(@JsonProperty(required = false, value = "id") String id,
                          @JsonProperty(required = true, value = "senderId") String senderId,
                          @JsonProperty(required = true, value = "recipientId") String recipientId,
                          @JsonProperty(required = true, value = "amount") String amount,
                          @JsonProperty(required = false, value = "date") Date date) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "{senderId: " + this.senderId + ", recipientId: " + this.recipientId + ", amount: " + this.amount +"}";
    }
}
