/*
 * Copyright 2025 Merly Abondano
 *
 * Created:   2025-06-09
 * Author:    Merly Abondano
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    //@Override
    public Response toResponse_old(ConstraintViolationException ex) {
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
    
    @Override
    public Response toResponse(ConstraintViolationException ex) {
        List<Violation> errs = new ArrayList<>();
        for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
            // Obtenemos el nombre real del elemento (último nodo)
            String fullPath = cv.getPropertyPath().toString();      // e.j. "create.arg0.email"
            String[] parts   = fullPath.split("\\.");
            String fieldName = parts[parts.length - 1];            // "email"

            errs.add(new Violation(fieldName, cv.getMessage()));
        }
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity(Collections.singletonMap("errors", errs))
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }

}
