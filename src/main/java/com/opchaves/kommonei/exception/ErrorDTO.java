package com.opchaves.kommonei.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorDTO {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public String errorId;

  public List<ErrorMessage> errors;

  public ErrorDTO() {
  }

  public ErrorDTO(String errorId, ErrorMessage message) {
    this.errorId = errorId;
    this.errors = List.of(message);
  }

  public ErrorDTO(ErrorMessage message) {
    this.errorId = null;
    this.errors = List.of(message);
  }

  public ErrorDTO(List<ErrorMessage> messages) {
    this.errorId = null;
    this.errors = messages;
  }

  public String getErrorId() {
    return this.errorId;
  }

  public List<ErrorMessage> getErrors() {
    return this.errors;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((errorId == null) ? 0 : errorId.hashCode());
    result = prime * result + ((errors == null) ? 0 : errors.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ErrorDTO other = (ErrorDTO) obj;
    if (errorId == null) {
      if (other.errorId != null)
        return false;
    } else if (!errorId.equals(other.errorId))
      return false;
    if (errors == null) {
      if (other.errors != null)
        return false;
    } else if (!errors.equals(other.errors))
      return false;
    return true;
  }
}
