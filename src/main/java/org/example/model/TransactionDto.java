package org.example.model;


public record TransactionDto(
        Category category,
        double amount
) {}