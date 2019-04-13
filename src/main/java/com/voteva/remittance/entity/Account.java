package com.voteva.remittance.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Bank account for user
 */
public class Account implements Serializable {
    private static final long serialVersionUID = -157332491738429002L;

    private String id;
    private String ownerId;
    private BigDecimal balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
