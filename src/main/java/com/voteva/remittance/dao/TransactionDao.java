package com.voteva.remittance.dao;

import com.voteva.remittance.entity.Transaction;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface TransactionDao {

    /**
     * Creates table for transactions
     */
    @SqlUpdate("CREATE TABLE transactions (" +
            "id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "senderId INTEGER NOT NULL, " +
            "recipientId INTEGER NOT NULL, " +
            "amount DECIMAL NOT NULL, " +
            "date DATETIME NOT NULL, " +
            "FOREIGN KEY (senderId) REFERENCES accounts(id), " +
            "FOREIGN KEY (recipientId) REFERENCES accounts(id))")
    void createTable();

    /**
     * Adds new transaction
     *
     * @param transaction transaction info
     */
    @SqlUpdate("INSERT INTO transactions(senderId, recipientId, amount, date) VALUES (:senderId, :recipientId, :amount, GETDATE())")
    void addTransaction(@BindBean Transaction transaction);

    /**
     * Returns all successful transactions for specified account id
     *
     * @param accountId account identifier
     * @return all successful transactions for specified account id
     */
    @SqlQuery("SELECT * FROM transactions WHERE senderId = :accountId OR recipientId = :accountId")
    @RegisterBeanMapper(Transaction.class)
    List<Transaction> getAllByAccountId(@Bind("accountId") String accountId);
}
