package net.reactiveapp.reactiveservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CompanyRequest(
        @NotEmpty(message = "Name of the company is required")
        String name,
        @NotNull(message = "Price is required")
        Double price) {}
