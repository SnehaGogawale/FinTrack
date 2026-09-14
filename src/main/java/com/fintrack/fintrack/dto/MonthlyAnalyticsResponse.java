package com.fintrack.fintrack.dto;

import java.util.Map;

public class MonthlyAnalyticsResponse {

    private String month;
    private Double income;
    private Double expense;
    private Double savings;
    private Map<String, Double> categoryExpenses;

    public MonthlyAnalyticsResponse() {
    }

    public MonthlyAnalyticsResponse(
            String month,
            Double income,
            Double expense,
            Double savings,
            Map<String, Double> categoryExpenses) {

        this.month = month;
        this.income = income;
        this.expense = expense;
        this.savings = savings;
        this.categoryExpenses = categoryExpenses;
    }

    public String getMonth() {
        return month;
    }

    public Double getIncome() {
        return income;
    }

    public Double getExpense() {
        return expense;
    }

    public Double getSavings() {
        return savings;
    }

    public Map<String, Double> getCategoryExpenses() {
        return categoryExpenses;
    }
}