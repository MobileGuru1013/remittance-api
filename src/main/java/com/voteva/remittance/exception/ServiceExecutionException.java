package com.voteva.remittance.exception;

/**
 * Runtime business exception
 */
public class ServiceExecutionException extends RuntimeException {

    public ServiceExecutionException(String message) {
        super(message);
    }
}
