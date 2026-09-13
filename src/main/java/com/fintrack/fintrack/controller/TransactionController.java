package com.fintrack.fintrack.controller;

import com.fintrack.fintrack.dto.TransactionRequest;
import com.fintrack.fintrack.dto.TransactionResponse;
import com.fintrack.fintrack.entity.Transaction;
import com.fintrack.fintrack.service.TransactionService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(
            @Valid @RequestBody TransactionRequest request,
            @AuthenticationPrincipal Jwt jwt) {

        Long userId = jwt.getClaim("userId");

        Transaction transaction =
                transactionService.createTransaction(request, userId);

        return ResponseEntity.ok(convertToResponse(transaction));
    }
    @PutMapping("/{id}")
public ResponseEntity<TransactionResponse> updateTransaction(
        @PathVariable Long id,
        @Valid @RequestBody TransactionRequest request,
        @AuthenticationPrincipal Jwt jwt) {

    Long userId = jwt.getClaim("userId");

    Transaction transaction =
            transactionService.updateTransaction(
                    id,
                    userId,
                    request
            );

    return ResponseEntity.ok(convertToResponse(transaction));
}

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactions(
            @AuthenticationPrincipal Jwt jwt) {

        Long userId = jwt.getClaim("userId");

        List<TransactionResponse> transactions =
                transactionService.getUserTransactions(userId)
                        .stream()
                        .map(this::convertToResponse)
                        .toList();

        return ResponseEntity.ok(transactions);
    }

    @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteTransaction(
        @PathVariable Long id,
        @AuthenticationPrincipal Jwt jwt) {

    Long userId = jwt.getClaim("userId");

    transactionService.deleteTransaction(id, userId);

    return ResponseEntity.noContent().build();
}

    private TransactionResponse convertToResponse(
            Transaction transaction) {

        return new TransactionResponse(
                transaction.getId(),
                transaction.getTitle(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getCategory(),
                transaction.getDate()
        );
    }
}