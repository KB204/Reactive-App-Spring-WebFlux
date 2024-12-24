package net.reactiveapp.reactiveclient.service;

import net.reactiveapp.reactiveclient.dto.ApiResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
    Flux<ApiResponse> getAllTransaction();
    Flux<Double> getCompanyTransaction(Long companyId);
    Mono<ApiResponse> getTransactionByIdentifier(String identifier);
}
