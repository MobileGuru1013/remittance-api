package com.voteva.remittance.service;

import com.voteva.remittance.dao.AccountDao;
import com.voteva.remittance.entity.Account;
import com.voteva.remittance.exception.ServiceExecutionException;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

import static com.voteva.remittance.utils.CollectionUtils.isNotEmpty;

public class AccountOperationService implements AccountOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountOperationService.class);
    private Jdbi jdbi;

    @Inject
    public AccountOperationService(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public List<Account> getAll(String userId) {
        try {
            List<Account> accounts = jdbi.withExtension(AccountDao.class, dao -> dao.getAllByOwnerId(userId));

            if (isNotEmpty(accounts)) {
                LOGGER.debug("Successfully get accounts for user with id={}", userId);
            } else {
                LOGGER.warn("Accounts for user with id={} not found", userId);
            }

            return accounts;
        } catch (Exception e) {
            LOGGER.error("Failed to get accounts for user with id={}. {}", userId, e.getMessage());
            throw new ServiceExecutionException(e.getMessage());
        }
    }
}
