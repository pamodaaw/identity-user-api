package org.wso2.carbon.identity.api.user.challenge.common.error;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class APIError extends WebApplicationException {

    private String message;

    public APIError(Response.Status status , Object errorResponse) {
        super(Response.status(status)
                .entity(errorResponse)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .build());
        this.message = status.getReasonPhrase();
    }

    public APIError(Response.Status status, String message, Object errorResponse) {
        this(status, errorResponse);
        this.message = message;
    }

    public APIError() {

        super(Response.Status.FORBIDDEN);
    }

    @Override
    public String getMessage() {

        return message;
    }
}
