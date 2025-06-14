/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merlab.stackflow.api.exception;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.Map;

/**
 *
 * @author merly
 */
@Provider
public class NotFoundExceptionMapper
    implements ExceptionMapper<NotFoundException> {
    
  @Override
  public Response toResponse(NotFoundException ex) {
    return Response.status(Response.Status.NOT_FOUND)
                   .entity(Map.of("error", ex.getMessage()))
                   .type(MediaType.APPLICATION_JSON)
                   .build();
  }
}
