package net.reactiveapp.reactiveservice.service;

import net.reactiveapp.reactiveservice.dto.TransactionRequest;
import net.reactiveapp.reactiveservice.dto.TransactionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {
    Flux<TransactionResponse> getAllTransactions();
    Mono<TransactionResponse> getTransactionByIdentifier(String identifier);
    Mono<TransactionRequest> createTransaction(TransactionRequest request);
}
