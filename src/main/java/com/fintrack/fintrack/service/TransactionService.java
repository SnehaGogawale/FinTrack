package com.fintrack.fintrack.service;

import com.fintrack.fintrack.dto.TransactionRequest;
import com.fintrack.fintrack.entity.Transaction;
import com.fintrack.fintrack.entity.User;
import com.fintrack.fintrack.repository.TransactionRepository;
import com.fintrack.fintrack.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(
            TransactionRepository transactionRepository,
            UserRepository userRepository) {

        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction createTransaction(
            TransactionRequest request,
            Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Transaction transaction = new Transaction(
                request.getTitle(),
                request.getAmount(),
                request.getType(),
                request.getCategory(),
                request.getDate(),
                user
        );

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getUserTransactions(Long userId) {

        return transactionRepository.findByUserId(userId);
    }

    public void deleteTransaction(Long transactionId, Long userId) {

    Transaction transaction = transactionRepository
            .findByIdAndUserId(transactionId, userId)
            .orElseThrow(() ->
                    new RuntimeException("Transaction not found"));

    transactionRepository.delete(transaction);
}
public Transaction updateTransaction(
        Long transactionId,
        Long userId,
        TransactionRequest request) {

    Transaction transaction = transactionRepository
            .findByIdAndUserId(transactionId, userId)
            .orElseThrow(() ->
                    new RuntimeException("Transaction not found"));

    transaction.setTitle(request.getTitle());
    transaction.setAmount(request.getAmount());
    transaction.setType(request.getType());
    transaction.setCategory(request.getCategory());
    transaction.setDate(request.getDate());

    return transactionRepository.save(transaction);
}
}