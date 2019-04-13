package com.voteva.remittance.service;

import com.voteva.remittance.dao.RemittanceDao;
import com.voteva.remittance.dao.TransactionDao;
import com.voteva.remittance.entity.Transaction;
import com.voteva.remittance.exception.ServiceExecutionException;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

import static com.voteva.remittance.utils.CollectionUtils.isNotEmpty;

public class TransactionOperationService implements TransactionOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionOperationService.class);
    private Jdbi jdbi;

    @Inject
    public TransactionOperationService(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public void perform(Transaction transaction) {
        try {
            jdbi.onDemand(RemittanceDao.class).perform(transaction);

            LOGGER.debug("Transaction successfully performed.");
        } catch (Exception e) {
            LOGGER.error("Failed to perform transaction. {}", e.getMessage());
            throw new ServiceExecutionException(e.getMessage());
        }
    }

    @Override
    public List<Transaction> getAll(String accountId) {
        try {
            List<Transaction> transactions = jdbi.withExtension(TransactionDao.class, dao -> dao.getAllByAccountId(accountId));

            if (isNotEmpty(transactions)) {
                LOGGER.debug("Successfully get transactions for account with id={}", accountId);
            } else {
                LOGGER.warn("Transactions for account with id={} not found", accountId);
            }

            return transactions;
        } catch (Exception e) {
            LOGGER.error("Failed to get transactions for account with id={}. {}", accountId, e.getMessage());
            throw new ServiceExecutionException(e.getMessage());
        }
    }
}
