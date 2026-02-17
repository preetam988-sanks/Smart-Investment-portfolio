package com.smartinvestment.smart_investment_portfolio.Service;

import com.smartinvestment.smart_investment_portfolio.DTO.InvestmentRequest;
import com.smartinvestment.smart_investment_portfolio.Entity.Investment;
import com.smartinvestment.smart_investment_portfolio.Entity.User;
import com.smartinvestment.smart_investment_portfolio.Repositories.InvestmentRepository;
import com.smartinvestment.smart_investment_portfolio.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class InvestmentService {
    private final InvestmentRepository investmentRepository;
    private final UserRepository userRepository;
    public Investment addInvestment(InvestmentRequest request,String email){
        User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found"));
        Investment investment=Investment.builder()
                .assetName(request.assetName())
                .quantity(request.quantity())
                .buyPrice(request.price())
                .user(user)
                .build();
        return investmentRepository.save(investment);
    }
    public List<Investment> getAllMyInvestment(String email){
        User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found"));
        return investmentRepository.findByUser(user);
    }
    public void deleteInvestment(Long id,String email){
        Investment investment=investmentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Investment Not Found"));
        if(!investment.getUser().getEmail().equals(email)){
            throw new RuntimeException("Unauthorized to delete this investment");
        }
        investmentRepository.delete(investment);
    }
}
