package net.reactiveapp.reactiveclient.controller;

import net.reactiveapp.reactiveclient.dto.ApiResponse;
import net.reactiveapp.reactiveclient.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/clientTransactions")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping(value = "/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Flux<ApiResponse> findAllTransactions() {
        return service.getAllTransaction().delayElements(Duration.ofSeconds(1L));
    }

    @GetMapping(value = "/{companyId}/companyTransactions",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Flux<Double> findAllCompanyTransactions(@PathVariable Long companyId) {
        return service.getCompanyTransaction(companyId).delayElements(Duration.ofSeconds(1L));
    }

    @GetMapping("/{identifier}/transactionDetails")
    @ResponseStatus(HttpStatus.OK)
    Mono<ApiResponse> findTransactionDetails(@PathVariable String identifier) {
        return service.getTransactionByIdentifier(identifier);
    }
}
