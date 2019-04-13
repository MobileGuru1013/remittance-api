package com.voteva.remittance;

import com.voteva.remittance.api.v1.AccountEndpoints;
import com.voteva.remittance.api.v1.TransactionEndpoints;
import com.voteva.remittance.api.v1.UserEndpoints;
import com.voteva.remittance.api.v1.response.ResponseObjectMapper;
import com.voteva.remittance.module.AppModule;
import com.voteva.remittance.module.JsonModule;
import com.voteva.remittance.module.MockDataSourceModule;
import dagger.Component;

import javax.inject.Singleton;

@Component(modules = {AppModule.class, JsonModule.class, MockDataSourceModule.class})
@Singleton
interface AppComponent {

    ResponseObjectMapper getResponseObjectMapper();
    AccountEndpoints getAccountEndpoints();
    TransactionEndpoints getTransactionEndpoints();
    UserEndpoints getUserEndpoints();
}
