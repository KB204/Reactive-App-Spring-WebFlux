package net.reactiveapp.reactiveservice.mapper;

import net.reactiveapp.reactiveservice.dto.TransactionRequest;
import net.reactiveapp.reactiveservice.dto.TransactionResponse;
import net.reactiveapp.reactiveservice.entities.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponse transactionToDtoResponse(Transaction transaction);
    TransactionRequest transactionToDtoRequest(Transaction transaction);
}
