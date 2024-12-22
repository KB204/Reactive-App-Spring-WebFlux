package net.reactiveapp.reactiveservice.dao;

import net.reactiveapp.reactiveservice.entities.Company;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CompanyRepository extends ReactiveCrudRepository<Company,Long> {
    Flux<Company> findByNameIgnoreCase(String name);
}
