package com.fintrack.fintrack.dto;

import java.util.Map;

public class DashboardResponse {

    private Double totalIncome;
    private Double totalExpense;
    private Double balance;
    private Map<String, Double> categoryExpenses;

    public DashboardResponse() {
    }

    public DashboardResponse(
            Double totalIncome,
            Double totalExpense,
            Double balance,
            Map<String, Double> categoryExpenses) {

        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.balance = balance;
        this.categoryExpenses = categoryExpenses;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public Double getBalance() {
        return balance;
    }

    public Map<String, Double> getCategoryExpenses() {
        return categoryExpenses;
    }
}