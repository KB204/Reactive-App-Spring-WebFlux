package net.reactiveapp.reactiveservice.service;

import net.reactiveapp.reactiveservice.dao.TransactionRepository;
import net.reactiveapp.reactiveservice.dto.TransactionRequest;
import net.reactiveapp.reactiveservice.dto.TransactionResponse;
import net.reactiveapp.reactiveservice.exceptions.ResourceNotFoundException;
import net.reactiveapp.reactiveservice.mapper.TransactionMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper mapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper mapper) {
        this.transactionRepository = transactionRepository;
        this.mapper = mapper;
    }

    @Override
    public Flux<TransactionResponse> getAllTransactions() {
        return transactionRepository.findAll().map(mapper::transactionToDtoResponse);
    }

    @Override
    public Mono<TransactionResponse> getTransactionByIdentifier(String identifier) {
        return transactionRepository.findByIdentifier(identifier)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(format("Transaction identified by %s not found",identifier))))
                .map(mapper::transactionToDtoResponse);
    }

    @Override
    public Mono<TransactionRequest> createTransaction(TransactionRequest request) {
        return null;
    }
}
