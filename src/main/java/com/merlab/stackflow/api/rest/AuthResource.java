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


package com.merlab.stackflow.api.rest;

import com.merlab.stackflow.api.dto.LoginDTO;
import com.merlab.stackflow.persistence.entity.Users;
import com.merlab.stackflow.service.UserServiceLocal;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    private UserServiceLocal userService;

    @POST
    public Response login(@Valid LoginDTO loginDTO) {
        Users user = userService.authenticate(loginDTO.username, loginDTO.password);
        if (user != null) {
            return Response.ok() //200 OK
                    .entity("{\"message\": \"Login successful\", \"userId\": " + user.getId() + "}")
                    .build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED) //401 Unauthorized
                    .entity("{\"error\": \"Invalid credentials\"}")
                    .build();
        }
    }
}
