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

package com.merlab.stackflow.api;

import com.merlab.stackflow.test.TestDBAccess;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/testdb")
public class TestDBResource {
    
    private static final Logger log = LoggerFactory.getLogger(TestDBResource.class);
    
    @Inject
    private TestDBAccess testDBAccess;

    @POST
    @Path("/insert")
    @Consumes(MediaType.TEXT_PLAIN)
    public void insertValue(String value) {
        testDBAccess.insertTestValue(value);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_PLAIN)
    public String readAll() {
        // Devuelve los resultados en un string simple
        StringBuilder sb = new StringBuilder();
        var lista = testDBAccess.findAll();
        lista.forEach(e -> sb.append("ID: ").append(e.getId())
                             .append(" | Value: ").append(e.getValue())
                             .append("\n"));
        return sb.toString();
    }
}