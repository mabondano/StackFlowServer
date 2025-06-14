package com.merlab.stackflow.api.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.*;

/**
 * ExceptionMapper para validaciones
 * @author merly
 */
@Provider
public class ValidationExceptionMapper
    implements ExceptionMapper<ConstraintViolationException> {

    public static class Violation {
        public String field;
        public String message;
        public Violation(String f, String m) { field = f; message = m; }
    }

    @Override
    public Response toResponse(ConstraintViolationException ex) {
        List<Violation> errs = new ArrayList<>();
        for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
            String path = cv.getPropertyPath().toString();
            errs.add(new Violation(path, cv.getMessage()));
        }
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity(Collections.singletonMap("errors", errs))
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}
