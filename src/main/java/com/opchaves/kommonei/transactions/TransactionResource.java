package com.opchaves.kommonei.transactions;

import java.util.List;

import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;

@Path("/api/transactions")
public class TransactionResource {

  TransactionService transactionService;

  public TransactionResource(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @POST
  public Uni<TransactionDTO> create(@Valid TransactionDTO input) {
    return transactionService.create(input);
  }

  @GET
  public Uni<List<TransactionDTO>> list() {
    return transactionService.list();
  }

  @GET
  @Path("/{id}")
  public Uni<TransactionDTO> get(String id) {
    return transactionService.getById(id)
        .onItem()
        .ifNull()
        .failWith(new WebApplicationException(404));
  }

  @PUT
  @Path("/{id}")
  public Uni<TransactionDTO> update(String id, @Valid TransactionDTO input) {
    return transactionService.update(id, input)
        .onItem()
        .ifNull()
        .failWith(new WebApplicationException(404));
  }

  @DELETE
  @Path("/{id}")
  public Uni<Void> delete(String id) {
    return transactionService.delete(id);
  }
}
