package com.smartinvestment.smart_investment_portfolio.Repositories;

import com.smartinvestment.smart_investment_portfolio.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
