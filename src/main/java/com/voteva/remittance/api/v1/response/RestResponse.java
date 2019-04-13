package com.voteva.remittance.api.v1.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"success", "body", "error"})
public class RestResponse {

    private boolean success;
    private Object body;
    private Object error;

    RestResponse() {
        this.success = true;
    }

    RestResponse(Object o) {
        this.success = true;
        this.body = o;
    }

    RestResponse(Exception e) {
        this.error = e.getMessage();
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getBody() {
        return body;
    }

    public Object getError() {
        return error;
    }
}
