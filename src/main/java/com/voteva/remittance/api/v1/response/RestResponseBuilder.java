package com.voteva.remittance.api.v1.response;

import javax.ws.rs.core.Response;

/**
 * Unified response from REST services.
 */
public abstract class RestResponseBuilder {

    /**
     * Builds response with successful status and empty body.
     *
     * @return response
     */
    public static Response build() {
        return Response.ok(new RestResponse()).build();
    }

    /**
     * Builds response with successful status and body.
     *
     * @param o response data
     * @return response
     */
    public static Response build(Object o) {
        return Response.ok(new RestResponse(o)).build();
    }

    /**
     * Builds response with error status.
     *
     * @param e exception
     * @return response
     */
    public static Response build(Exception e) {
        return Response.ok(new RestResponse(e)).build();
    }
}
