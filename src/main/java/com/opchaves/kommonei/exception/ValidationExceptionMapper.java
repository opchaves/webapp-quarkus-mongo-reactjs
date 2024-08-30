package com.opchaves.kommonei.exception;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

  public Response toResponse(ConstraintViolationException e) {
    List<ErrorMessage> messages = e.getConstraintViolations()
        .stream()
        .map(violation -> {
          String path = violation.getPropertyPath().toString();
          String message = violation.getMessage();
          return new ErrorMessage(path, message);
        })
        .collect(Collectors.toList());
    ErrorDTO error = new ErrorDTO(messages);
    return Response.status(BAD_REQUEST).entity(error).build();
  }

}
