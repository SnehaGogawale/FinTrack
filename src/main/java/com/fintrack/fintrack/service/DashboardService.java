package com.fintrack.fintrack.service;

import com.fintrack.fintrack.dto.DashboardResponse;
import com.fintrack.fintrack.entity.Transaction;
import com.fintrack.fintrack.entity.TransactionType;
import com.fintrack.fintrack.repository.TransactionRepository;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final TransactionRepository transactionRepository;

    public DashboardService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public DashboardResponse getDashboard(Long userId) {

        List<Transaction> transactions =
                transactionRepository.findByUserId(userId);

        double totalIncome = transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
.mapToDouble(t -> t.getAmount()).sum();

        double totalExpense = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .mapToDouble(t -> t.getAmount()).sum();

        double balance = totalIncome - totalExpense;

        Map<String, Double> categoryExpenses = new HashMap<>();

        transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .forEach(t -> categoryExpenses.merge(
        t.getCategory(),
        t.getAmount(),
        (existing, amount) -> existing + amount
));

        return new DashboardResponse(
                totalIncome,
                totalExpense,
                balance,
                categoryExpenses
        );
    }
}