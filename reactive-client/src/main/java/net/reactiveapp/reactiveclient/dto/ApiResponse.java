package net.reactiveapp.reactiveclient.dto;

import java.time.LocalDateTime;

public record ApiResponse(String identifier, LocalDateTime createdAt, Double amount, Long companyId) {}
