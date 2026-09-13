package com.fintrack.fintrack.repository;

import com.fintrack.fintrack.entity.Transaction;
import com.fintrack.fintrack.entity.TransactionType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserId(Long userId);

    List<Transaction> findByUserIdAndType(
            Long userId,
            TransactionType type
    );

    Optional<Transaction> findByIdAndUserId(
            Long id,
            Long userId
    );
}