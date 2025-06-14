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

import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;
import java.util.Collections;

/**
 *
 * @author merly
 */
@Provider
public class IllegalArgumentExceptionMapper
    implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(IllegalArgumentException ex) {
        return Response.status(Response.Status.CONFLICT)   // 409
                       .entity(Collections.singletonMap("error", ex.getMessage()))
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}

