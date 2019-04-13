package com.voteva.remittance.module;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voteva.remittance.dao.AccountDao;
import com.voteva.remittance.dao.TransactionDao;
import com.voteva.remittance.dao.UserDao;
import com.voteva.remittance.entity.Account;
import com.voteva.remittance.entity.User;
import dagger.Module;
import dagger.Provides;
import org.h2.jdbcx.JdbcDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.inject.Singleton;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Module
public class MockDataSourceModule {

    @Provides
    @Singleton
    DataSource provideDataSource() {
        JdbcDataSource ds = new JdbcDataSource();

        // Use H2 in-memory database
        ds.setURL("jdbc:h2:mem:remittance;DB_CLOSE_DELAY=-1");

        return ds;
    }

    @Provides
    @Singleton
    Jdbi provideJdbi(DataSource dataSource) {
        Jdbi jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());

        List<User> users = getMockUsers();
        List<Account> accounts = new ArrayList<>();

        // Create table for users and fill mock data
        jdbi.useExtension(UserDao.class, dao -> {
            dao.createTable();
            for (User user : users) {
                dao.addUser(user);
                if (user.getAccounts() != null) {
                    accounts.addAll(user.getAccounts());
                }
            }
        });

        // Create table for accounts and fill mock data
        jdbi.useExtension(AccountDao.class, dao -> {
            dao.createTable();
            for (Account account : accounts) {
                dao.addAccount(account);
            }
        });

        // Create table for transactions
        jdbi.useExtension(TransactionDao.class, TransactionDao::createTable);

        return jdbi;
    }

    private List<User> getMockUsers() {
        List<User> users = new ArrayList<>();
        File file = new File("src/main/resources/mock/users.json");

        try (Reader fileReader = new FileReader(file)) {
            users = new ObjectMapper().readValue(fileReader, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
}
