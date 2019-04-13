package com.voteva.remittance.dao;

import com.voteva.remittance.entity.Account;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    /**
     * Creates table for user accounts
     */
    @SqlUpdate("CREATE TABLE accounts (" +
            "id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "ownerId INTEGER NOT NULL, " +
            "balance DECIMAL NOT NULL CHECK (balance >= 0), " +
            "FOREIGN KEY (ownerId) REFERENCES users(id))")
    void createTable();

    /**
     * Adds new account
     *
     * @param account account info
     */
    @SqlUpdate("INSERT INTO accounts(ownerId, balance) VALUES (:ownerId, :balance)")
    void addAccount(@BindBean Account account);

    /**
     * Updates balance for specified account
     *
     * @param id account identifier
     * @param balance new balance
     */
    @SqlUpdate("UPDATE accounts SET balance = :balance WHERE id = :id")
    void updateBalance(@Bind("id") String id, @Bind("balance") BigDecimal balance);

    /**
     * Returns account information by specified id
     *
     * @param id account identifier
     * @return account information by specified id
     */
    @SqlQuery("SELECT * FROM accounts WHERE id = :id")
    @RegisterBeanMapper(Account.class)
    Account getById(@Bind("id") String id);

    /**
     * Returns all accounts for current user
     *
     * @param ownerId user identifier
     * @return all accounts for current user
     */
    @SqlQuery("SELECT * FROM accounts WHERE ownerId = :ownerId")
    @RegisterBeanMapper(Account.class)
    List<Account> getAllByOwnerId(@Bind("ownerId") String ownerId);
}
