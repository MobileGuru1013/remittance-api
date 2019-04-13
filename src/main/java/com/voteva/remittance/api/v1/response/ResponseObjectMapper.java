package com.voteva.remittance.api.v1.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.ext.ContextResolver;

@Singleton
public class ResponseObjectMapper implements ContextResolver<ObjectMapper> {
    private final ObjectMapper objectMapper;

    @Inject
    ResponseObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }
}
