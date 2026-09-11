package com.fintrack.fintrack.repository;

import com.fintrack.fintrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}