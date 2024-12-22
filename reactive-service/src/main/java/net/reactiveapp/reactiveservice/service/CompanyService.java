package net.reactiveapp.reactiveservice.service;

import net.reactiveapp.reactiveservice.dto.CompanyRequest;
import net.reactiveapp.reactiveservice.dto.CompanyResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyService {
    Flux<CompanyResponse> getAllCompanies();
    Flux<CompanyResponse> getCompaniesByName(String name);
    Mono<CompanyRequest> createCompany(CompanyRequest request);
    Mono<CompanyRequest> updateCompany(Long id,CompanyRequest request);
}
