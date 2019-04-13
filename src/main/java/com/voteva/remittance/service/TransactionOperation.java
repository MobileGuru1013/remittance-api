package com.voteva.remittance.service;

import com.voteva.remittance.entity.Transaction;

import java.util.List;

/**
 * Provides operations with transactions.
 */
public interface TransactionOperation {

    /**
     * Performs transaction.
     *
     * @param transaction transaction info
     */
    void perform(Transaction transaction);

    /**
     * Returns all successful transactions for specified account identifier.
     *
     * @param accountId account identifier
     * @return all successful transactions for specified account identifier
     */
    List<Transaction> getAll(String accountId);
}
