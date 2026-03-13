package org.example;

import org.example.model.Category;
import org.example.model.TransactionDto;
import org.example.service.TransactionService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Main {
    static void main() {
        var data = List.of(
                new TransactionDto(Category.FOOD, 100),
                new TransactionDto(Category.FOOD, 50),
                new TransactionDto(Category.FOOD, 57),
                new TransactionDto(Category.TRANSPORT, 70),
                new TransactionDto(Category.ENTERTAINMENT, 200),
                new TransactionDto(Category.ENTERTAINMENT, 200),
                new TransactionDto(Category.ENTERTAINMENT, 200)
        );

        var result =
                TransactionService.sumAmountPerCategoryUsingLoop(data);

        System.out.println(result);


        List<String> names = Arrays.asList("Jake", "Sophia", "Lucas", "Mia", "Benjamin");
        Map<Integer, List<String>> groupedByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println(groupedByLength);
    }

}