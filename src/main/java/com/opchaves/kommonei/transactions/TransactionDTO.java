package com.opchaves.kommonei.transactions;

import java.time.LocalDateTime;

public class TransactionDTO {
  public String id;
  public String title;
  public String description;
  public Double amount;
  public Boolean paid;
  public String category;
  public String type;
  public LocalDateTime handledAt;
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;

  public TransactionDTO() {
  }
}
