package net.reactiveapp.reactiveservice.dao;

import net.reactiveapp.reactiveservice.entities.Transaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction,Long> {
    Mono<Transaction> findByIdentifier(String identifier);
    Flux<Transaction> findByCompanyId(Long id);
}
