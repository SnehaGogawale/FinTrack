package com.fintrack.fintrack.service;

import com.fintrack.fintrack.dto.MonthlyAnalyticsResponse;
import com.fintrack.fintrack.entity.Transaction;
import com.fintrack.fintrack.entity.TransactionType;
import com.fintrack.fintrack.repository.TransactionRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {

    private final TransactionRepository transactionRepository;

    public AnalyticsService(
            TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public MonthlyAnalyticsResponse getMonthlyAnalytics(
            Long userId,
            int year,
            int month) {

        List<Transaction> transactions =
                transactionRepository.findByUserId(userId);

        YearMonth requestedMonth =
                YearMonth.of(year, month);

        double income = 0;
        double expense = 0;

        Map<String, Double> categoryExpenses =
                new HashMap<>();

        for (Transaction transaction : transactions) {

            LocalDate date = transaction.getDate();

            if (!YearMonth.from(date).equals(requestedMonth)) {
                continue;
            }

            if (transaction.getType() == TransactionType.INCOME) {

                income += transaction.getAmount();

            } else if (transaction.getType() == TransactionType.EXPENSE) {

                expense += transaction.getAmount();

                categoryExpenses.merge(
                        transaction.getCategory(),
                        transaction.getAmount(),
                        (existing, amount) -> existing + amount
                );
            }
        }

        double savings = income - expense;

        return new MonthlyAnalyticsResponse(
                requestedMonth.toString(),
                income,
                expense,
                savings,
                categoryExpenses
        );
    }
}