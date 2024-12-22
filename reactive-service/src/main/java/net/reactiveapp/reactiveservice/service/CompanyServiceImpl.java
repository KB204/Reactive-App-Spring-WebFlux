package net.reactiveapp.reactiveservice.service;

import net.reactiveapp.reactiveservice.dao.CompanyRepository;
import net.reactiveapp.reactiveservice.dto.CompanyRequest;
import net.reactiveapp.reactiveservice.dto.CompanyResponse;
import net.reactiveapp.reactiveservice.entities.Company;
import net.reactiveapp.reactiveservice.exceptions.ResourceNotFoundException;
import net.reactiveapp.reactiveservice.mapper.CompanyMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository repository;
    private final CompanyMapper mapper;

    public CompanyServiceImpl(CompanyRepository repository, CompanyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Flux<CompanyResponse> getAllCompanies() {
        return repository.findAll().map(mapper::companyToDtoResponse);
    }

    @Override
    public Flux<CompanyResponse> getCompaniesByName(String name) {
        return repository.findByNameIgnoreCase(name)
                .switchIfEmpty(Flux.error(new ResourceNotFoundException(format("Company %s not found",name))))
                .map(mapper::companyToDtoResponse);
    }

    @Override
    public Mono<CompanyRequest> createCompany(CompanyRequest request) {
        return repository.save(mapper.requestDtoToCompany(request)).map(mapper::companyToRequestDTO);
    }

    @Override
    public Mono<CompanyRequest> updateCompany(Long id, CompanyRequest request) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(format("Company %s not found",id))))
                .flatMap(existingCompany -> {
                    Company company = mapper.requestDtoToCompany(request);
                    return repository.save(company);
                })
                .map(mapper::companyToRequestDTO);
    }
}
