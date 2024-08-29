package com.opchaves.kommonei.transactions;

import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi", imports = { LocalDateTime.class })
public interface TransactionMapper {

  @Mapping(target = "id", expression = "java(t.id.toString())")
  TransactionDTO toDTO(Transaction t);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "handledAt", defaultExpression = "java(LocalDateTime.now())")
  Transaction toEntity(TransactionDTO t);
}
