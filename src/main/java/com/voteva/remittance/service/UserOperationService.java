package com.voteva.remittance.service;

import com.voteva.remittance.dao.UserDao;
import com.voteva.remittance.entity.User;
import com.voteva.remittance.exception.ServiceExecutionException;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class UserOperationService implements UserOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserOperationService.class);
    private Jdbi jdbi;

    @Inject
    public UserOperationService(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public User getUser(String id) {
        try {
            User user = jdbi.withExtension(UserDao.class, dao -> dao.getById(id));

            if (user == null) {
                LOGGER.debug("Successfully get user with id={}", id);
            } else {
                LOGGER.warn("User with id={} not found", id);
            }

            return user;
        } catch (Exception e) {
            LOGGER.error("Failed to get user with id={}. {}", id, e.getMessage());
            throw new ServiceExecutionException(e.getMessage());
        }
    }
}
