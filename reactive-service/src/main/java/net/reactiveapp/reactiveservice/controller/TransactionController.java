package net.reactiveapp.reactiveservice.controller;

import jakarta.validation.Valid;
import net.reactiveapp.reactiveservice.dto.TransactionRequest;
import net.reactiveapp.reactiveservice.dto.TransactionResponse;
import net.reactiveapp.reactiveservice.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping(value = "/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Flux<TransactionResponse> findAllTransaction() {
        return service.getAllTransactions().delayElements(Duration.ofSeconds(1L));
    }

    @GetMapping("/{identifier}/transaction")
    @ResponseStatus(HttpStatus.OK)
    Mono<TransactionResponse> findTransactionByIdentifier(@PathVariable String identifier) {
        return service.getTransactionByIdentifier(identifier);
    }

    @GetMapping(value = "/{companyId}/companyTransactions",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Flux<TransactionResponse> findCompanyTransactions(@PathVariable Long companyId) {
        return service.companyTransactions(companyId).delayElements(Duration.ofSeconds(1L));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<TransactionRequest> saveNewCompanyTransaction(@RequestParam Long companyId, @RequestBody @Valid TransactionRequest request) {
        return service.createTransaction(companyId, request);
    }
}
