package net.reactiveapp.reactiveclient.service;

import net.reactiveapp.reactiveclient.dto.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {
    private final WebClient webClient;

    public ClientServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Flux<ApiResponse> getAllTransaction() {
        return webClient.get()
                .uri("/api/transactions/all")
                .retrieve()
                .bodyToFlux(ApiResponse.class);
    }

    @Override
    public Flux<Double> getCompanyTransaction(Long companyId) {
        return webClient.get()
                .uri("/api/transactions/{companyId}/companyTransactions",companyId)
                .retrieve()
                .bodyToFlux(ApiResponse.class)
                .map(ApiResponse::amount);
    }

    @Override
    public Mono<ApiResponse> getTransactionByIdentifier(String identifier) {
        return webClient.get()
                .uri("/api/transactions/{identifier}/transaction",identifier)
                .retrieve()
                .bodyToMono(ApiResponse.class);
    }
}
