package com.opchaves.kommonei.transactions;

import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public class TransactionDTO {
  public String id;

  @NotBlank(message = "Title is required")
  public String title;
  public String description;

  @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
  public Double amount;
  public Boolean paid;

  @NotBlank(message = "Category is required")
  public String category;

  @NotBlank(message = "Type is required")
  public String type;

  public LocalDateTime handledAt;
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;

  public TransactionDTO() {
  }
}
