package net.reactiveapp.reactiveservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record TransactionRequest(
        @NotNull(message = "Amount is required")
        @Min(value = 100,message = "Amount cant' be less that 100")
        Double amount) {}
