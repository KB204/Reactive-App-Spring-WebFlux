package net.reactiveapp.reactiveservice.service;

import net.reactiveapp.reactiveservice.dao.CompanyRepository;
import net.reactiveapp.reactiveservice.dao.TransactionRepository;
import net.reactiveapp.reactiveservice.dto.TransactionRequest;
import net.reactiveapp.reactiveservice.dto.TransactionResponse;
import net.reactiveapp.reactiveservice.entities.Transaction;
import net.reactiveapp.reactiveservice.exceptions.ResourceNotFoundException;
import net.reactiveapp.reactiveservice.mapper.TransactionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.String.format;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CompanyRepository companyRepository;
    private final TransactionMapper mapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, CompanyRepository companyRepository, TransactionMapper mapper) {
        this.transactionRepository = transactionRepository;
        this.companyRepository = companyRepository;
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
    public Flux<TransactionResponse> companyTransactions(Long companyId) {
        return transactionRepository.findByCompanyId(companyId)
                .switchIfEmpty(Flux.error(new ResourceNotFoundException(format("Company with id %s not found",companyId))))
                .map(mapper::transactionToDtoResponse);
    }
    @Override
    @Transactional
    public Mono<TransactionRequest> createTransaction(Long companyId, TransactionRequest request) {
        return companyRepository.findById(companyId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(format("Company with id %s not found",companyId))))
                .flatMap(company -> {
                    Transaction transaction = Transaction.builder()
                            .identifier(UUID.randomUUID().toString().substring(0,10))
                            .createdAt(LocalDateTime.now())
                            .amount(request.amount())
                            .companyId(companyId)
                            .build();
                    return transactionRepository.save(transaction);
                })
                .map(mapper::transactionToDtoRequest);
    }

}
