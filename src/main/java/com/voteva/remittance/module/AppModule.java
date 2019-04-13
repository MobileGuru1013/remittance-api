package com.voteva.remittance.module;

import com.voteva.remittance.service.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AppModule {

    @Provides
    @Singleton
    AccountOperation provideAccountOperation(AccountOperationService accountOperation) {
        return accountOperation;
    }

    @Provides
    @Singleton
    TransactionOperation provideTransactionOperation(TransactionOperationService transactionOperation) {
        return transactionOperation;
    }

    @Provides
    @Singleton
    UserOperation provideUserOperation(UserOperationService userOperation) {
        return userOperation;
    }
}
