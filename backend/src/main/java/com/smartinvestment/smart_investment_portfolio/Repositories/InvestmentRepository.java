package com.smartinvestment.smart_investment_portfolio.Repositories;

import com.smartinvestment.smart_investment_portfolio.Entity.Investment;
import com.smartinvestment.smart_investment_portfolio.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment,Long> {
    List<Investment> findByUser(User user);

}
