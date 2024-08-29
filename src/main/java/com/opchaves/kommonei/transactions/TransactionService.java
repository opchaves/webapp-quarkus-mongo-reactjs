package com.opchaves.kommonei.transactions;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionService {

  TransactionMapper mapper;

  public TransactionService(TransactionMapper transactionMapper) {
    this.mapper = transactionMapper;
  }

  public Uni<TransactionDTO> create(TransactionDTO input) {
    var transaction = mapper.toEntity(input);

    transaction.createdAt = LocalDateTime.now();
    transaction.updatedAt = LocalDateTime.now();
    if (transaction.handledAt == null) {
      transaction.handledAt = LocalDateTime.now();
    }

    return transaction.<Transaction>persist()
        .onItem()
        .transform(mapper::toDTO);
  }

  public Uni<List<TransactionDTO>> list() {
    return Transaction.<Transaction>listAll().onItem().transform(transactions -> {
      return transactions.stream().map(t -> mapper.toDTO(t)).toList();
    });
  }

  public Uni<TransactionDTO> getById(String id) {
    return Transaction.<Transaction>findById(new ObjectId(id))
        .onItem()
        .ifNotNull()
        .transform(mapper::toDTO);
  }

  public Uni<TransactionDTO> update(String id, TransactionDTO input) {
    return Transaction.<Transaction>findById(new ObjectId(id))
        .onItem().ifNotNull()
        .call(t -> {
          t.title = input.title;
          t.description = input.description;
          t.amount = input.amount;
          t.paid = input.paid;
          t.category = input.category;
          t.handledAt = input.handledAt;
          t.updatedAt = LocalDateTime.now();
          return t.<Transaction>update();
        })
        .onItem()
        .transform(mapper::toDTO);
  }

  public Uni<Void> delete(String id) {
    return Transaction.<Transaction>findById(new ObjectId(id))
        .onItem()
        .ifNotNull()
        .call(t -> t.delete())
        .replaceWithVoid();
  }
}
