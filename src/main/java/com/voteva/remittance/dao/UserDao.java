package com.voteva.remittance.dao;

import com.voteva.remittance.entity.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface UserDao {

    /**
     * Creates table for users
     */
    @SqlUpdate("CREATE TABLE users (" +
            "id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "firstName VARCHAR NOT NULL, " +
            "lastName VARCHAR NOT NULL, " +
            "middleName VARCHAR, " +
            "birthDate DATETIME NOT NULL)")
    void createTable();

    /**
     * Adds new user
     *
     * @param user user info
     */
    @SqlUpdate("INSERT INTO users(id, firstName, lastName, middleName, birthDate) VALUES (:id, :firstName, :lastName, :middleName, :birthDate)")
    void addUser(@BindBean User user);

    /**
     * Returns user info by id
     *
     * @param id user identifier
     * @return user info
     */
    @SqlQuery("SELECT * FROM users WHERE id = :id")
    @RegisterBeanMapper(User.class)
    User getById(@Bind("id") String id);
}
