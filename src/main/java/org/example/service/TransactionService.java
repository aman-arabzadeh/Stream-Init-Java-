package org.example.service;

import org.example.model.Category;
import org.example.model.TransactionDto;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

public class TransactionService {
    public static Map<Category, Double> sumAmountPerCategoryUsingLoop(List<TransactionDto> transactions) {
        if (transactions == null) return Collections.emptyMap();
        Map<Category, Double> result = new HashMap<>();

        for (var transaction : transactions) {
            if (transaction != null && transaction.category() != null) {
                var cat = transaction.category();
                var amount = transaction.amount();
                result.put(cat, result.getOrDefault(cat, 0.0) + amount);
            }
        }
        return result;
    }

    public static Map<Category, Double> sumAmountPerCategoryBad(List<TransactionDto> transactions) {
        if (transactions == null) return Collections.emptyMap();

        Map<Category, Double> result = new HashMap<>();
        Set<Category> categories = transactions.stream()
                .map(TransactionDto::category)
                .collect(Collectors.toSet());

        categories.forEach(category -> result.put(category, transactions.stream()
                .filter(t -> t.category().equals(category))
                .mapToDouble(TransactionDto::amount)
                .sum()));
        return result;
    }


    public static Map<Category, Double> sumAmountPerCategoryGoodV1(List<TransactionDto> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            return Map.of();
        }

        return transactions.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        TransactionDto::category,
                        TransactionDto::amount,
                        Double::sum
                ));
    }


    public static Map<Category, Double> sumAmountPerCategoryGoodV2(List<TransactionDto> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            return Map.of();
        }

        return transactions.stream()
                .filter(Objects::nonNull)
                .collect(groupingBy(
                        TransactionDto::category,
                        summingDouble(TransactionDto::amount)
                ));
    }

    public static Map<Category, Double> sumAmountPerCategoryPro(List<TransactionDto> transactions) {
        return Optional.ofNullable(transactions)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .collect(groupingBy(
                        TransactionDto::category,
                        summingDouble(TransactionDto::amount)
                ));
    }


}