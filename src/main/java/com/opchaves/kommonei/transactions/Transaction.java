package com.opchaves.kommonei.transactions;

import java.time.LocalDateTime;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

@MongoEntity(collection = "transactions")
public class Transaction extends ReactivePanacheMongoEntity {
  public String title;
  public String description;
  public Double amount;
  public Boolean paid;
  public String category;
  public String type;
  public LocalDateTime handledAt;
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;

  public Transaction() {
  }
}
