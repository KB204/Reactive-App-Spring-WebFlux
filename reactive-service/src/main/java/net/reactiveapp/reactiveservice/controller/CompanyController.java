package net.reactiveapp.reactiveservice.controller;

import jakarta.validation.Valid;
import net.reactiveapp.reactiveservice.dto.CompanyRequest;
import net.reactiveapp.reactiveservice.dto.CompanyResponse;
import net.reactiveapp.reactiveservice.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Flux<CompanyResponse> findAllCompanies() {
        return service.getAllCompanies().delayElements(Duration.ofSeconds(1L));
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    Flux<CompanyResponse> findCompanies(@PathVariable String name) {
        return service.getCompaniesByName(name);
    }

    @PostMapping("/newCompany")
    @ResponseStatus(HttpStatus.CREATED)
    Mono<CompanyRequest> saveNewCompany(@RequestBody @Valid CompanyRequest request) {
        return service.createCompany(request);
    }

    @PutMapping("/{id}/company")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Mono<CompanyRequest> editCompany(@PathVariable Long id, @RequestBody @Valid CompanyRequest request) {
        return service.updateCompany(id, request);
    }
}
