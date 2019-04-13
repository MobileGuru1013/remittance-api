package com.voteva.remittance.dao;

import com.voteva.remittance.entity.Account;
import org.jdbi.v3.sqlobject.CreateSqlObject;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.math.BigDecimal;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static org.jdbi.v3.core.transaction.TransactionIsolationLevel.REPEATABLE_READ;

public interface RemittanceDao {

    @CreateSqlObject
    AccountDao accountDao();
    @CreateSqlObject
    TransactionDao transactionDao();

    /**
     * Performs transaction
     *
     * @param transaction transaction info
     */
    @Transaction(REPEATABLE_READ)
    default void perform(com.voteva.remittance.entity.Transaction transaction) {
        if (transaction.getAmount().compareTo(ZERO) <= 0) {
            throw new IllegalArgumentException("Not possible to transfer negative or zero amount.");
        }

        Account senderAccount = accountDao().getById(transaction.getSenderId());
        if (senderAccount == null) {
            throw new IllegalArgumentException(format("Account with id=%s does not exists.", transaction.getSenderId()));
        }

        Account recipientAccount = accountDao().getById(transaction.getRecipientId());
        if (recipientAccount == null) {
            throw new IllegalArgumentException(format("Account with id=%s does not exists.", transaction.getRecipientId()));
        }

        BigDecimal senderNewBalance = senderAccount.getBalance().subtract(transaction.getAmount());
        if (senderNewBalance.compareTo(ZERO) < 0) {
            throw new IllegalArgumentException("Required amount exceeds the current balance.");
        }

        BigDecimal recipientNewBalance = recipientAccount.getBalance().add(transaction.getAmount());

        accountDao().updateBalance(transaction.getSenderId(), senderNewBalance);
        accountDao().updateBalance(transaction.getRecipientId(), recipientNewBalance);
        transactionDao().addTransaction(transaction);
    }
}
