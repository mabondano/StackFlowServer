package com.merlab.stackflow.api.exception;

import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;
import java.util.Collections;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable ex) {
        // Loguea el stacktrace en servidor
        ex.printStackTrace();
        // Devuelve 500 con un JSON genérico
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .entity(Collections.singletonMap(
                           "error", "An internal server error occurred"
                       ))
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}
