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

import com.merlab.stackflow.service.UserServiceLocal;
import com.merlab.stackflow.persistence.entity.Users;
import com.merlab.stackflow.api.dto.UserDTO;
import jakarta.ejb.EJB;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;
import java.util.stream.Collectors;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @EJB
    private UserServiceLocal svc;

    private UserDTO toDto(Users u) {
        UserDTO d = new UserDTO();
        d.id       = u.getId();
        d.username = u.getUsername();
        d.email    = u.getEmail();
        return d;
    }
    private Users fromDto(UserDTO d) {
        Users u = new Users();
        u.setId(d.id);
        u.setUsername(d.username);
        u.setEmail(d.email);
        // client debe enviar password en otro campo si queremos
        return u;
    }

    @GET
    public List<UserDTO> list() {
        return svc.listUsers().stream()
                  .map(this::toDto)
                  .collect(Collectors.toList());
    }

    @GET @Path("{id:\\d+}")
    public Response get(@PathParam("id") Long id) {
        Users u = svc.getUserById(id);
        if (u == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(toDto(u)).build();
    }

    @POST
    public Response create(@Valid UserDTO d, @Context UriInfo uriInfo) {
        Users u = fromDto(d);
        // aquí deberíamos leer contraseña de un DTO extendido
        Users saved = svc.registerUser(u);
        UriBuilder b = uriInfo.getAbsolutePathBuilder().path(saved.getId().toString());
        return Response.created(b.build()).entity(toDto(saved)).build();
    }

    @PUT @Path("{id}")
    public Response update(@PathParam("id") Long id, @Valid UserDTO d) {
        d.id = id;
        Users u = fromDto(d);
        Users updated = svc.updateUser(u);
        return Response.ok(toDto(updated)).build();
    }

    @DELETE @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        svc.deleteUser(id);
        return Response.noContent().build();
    }
    
    @GET
    @Path("trigger-error")
    public void triggerError() {
        throw new RuntimeException("Test unexpected error");
    }
}

