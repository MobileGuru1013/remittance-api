package com.voteva.remittance.service;

import com.voteva.remittance.entity.User;

/**
 * Provides operations with users.
 */
public interface UserOperation {

    /**
     * Returns user info by id.
     *
     * @param id user identifier
     * @return user info
     */
    User getUser(String id);
}
