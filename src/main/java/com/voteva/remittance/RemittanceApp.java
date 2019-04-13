package com.voteva.remittance;

import com.voteva.remittance.api.v1.response.RestExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;

public class RemittanceApp extends ResourceConfig {

    public RemittanceApp() {
        AppComponent appComponent = DaggerAppComponent.create();
        register(appComponent.getResponseObjectMapper());
        register(appComponent.getAccountEndpoints());
        register(appComponent.getTransactionEndpoints());
        register(appComponent.getUserEndpoints());
        register(RestExceptionMapper.class);
    }
}
