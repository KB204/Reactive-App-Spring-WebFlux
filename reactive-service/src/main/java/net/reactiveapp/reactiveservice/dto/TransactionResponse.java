package net.reactiveapp.reactiveservice.dto;

import java.time.LocalDateTime;

public record TransactionResponse(String identifier,LocalDateTime createdAt,Double amount,Long companyId) {}
