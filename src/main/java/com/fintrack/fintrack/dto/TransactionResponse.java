package com.fintrack.fintrack.dto;

import com.fintrack.fintrack.entity.TransactionType;

import java.time.LocalDate;

public class TransactionResponse {

    private Long id;
    private String title;
    private Double amount;
    private TransactionType type;
    private String category;
    private LocalDate date;

    public TransactionResponse() {
    }

    public TransactionResponse(
            Long id,
            String title,
            Double amount,
            TransactionType type,
            String category,
            LocalDate date) {

        this.id = id;
        this.title = title;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }
}