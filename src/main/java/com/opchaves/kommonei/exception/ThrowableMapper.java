package com.opchaves.kommonei.exception;

import java.util.UUID;

import io.quarkus.logging.Log;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {

  public Response toResponse(Throwable e) {
    if (e instanceof WebApplicationException) {
      Log.infof("Error: %s", e.getMessage());
      var originalResponse = ((WebApplicationException) e).getResponse();
      var message = new ErrorMessage(e.getMessage());
      return Response.fromResponse(originalResponse).entity(message).build();
    }

    String errorId = UUID.randomUUID().toString();
    Log.errorf(e, "Error ID: %s", errorId);
    String defaultMsg = "An unexpected error occurred. Please try again later.";
    var message = new ErrorMessage(defaultMsg);
    var error = new ErrorDTO(errorId, message);

    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
  }
}
