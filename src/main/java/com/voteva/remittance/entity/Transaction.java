package com.voteva.remittance.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Money transaction between 2 accounts
 */
public class Transaction implements Serializable {
    private static final long serialVersionUID = 389007873312348998L;

    private String id;
    private String senderId;
    private String recipientId;
    private BigDecimal amount;
    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
