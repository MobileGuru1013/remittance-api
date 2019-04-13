package com.voteva.remittance.service;

import com.voteva.remittance.entity.Account;

import java.util.List;

/**
 * Provides operations with user accounts.
 */
public interface AccountOperation {

    /**
     * Returns all accounts for specified user identifier.
     *
     * @param userId user identifier
     * @return all accounts for specified user identifier
     */
    List<Account> getAll(String userId);
}
