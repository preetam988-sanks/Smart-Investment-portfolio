package com.smartinvestment.smart_investment_portfolio.Controller;

import com.smartinvestment.smart_investment_portfolio.DTO.InvestmentRequest;
import com.smartinvestment.smart_investment_portfolio.Entity.Investment;
import com.smartinvestment.smart_investment_portfolio.Service.InvestmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/investments")
@RequiredArgsConstructor
public class InvestmentController  {
    private final InvestmentService investmentService;
    @PostMapping
    public ResponseEntity<Investment> create(@Valid @RequestBody InvestmentRequest request, Principal principal) {
        return ResponseEntity.ok(investmentService.addInvestment(request,principal.getName()));
    }
    @GetMapping
    public ResponseEntity<List<Investment>> getAll(Principal principal) {
        return ResponseEntity.ok(investmentService.getAllMyInvestment(principal.getName()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,Principal principal) {
     investmentService.deleteInvestment(id,principal.getName());
     return ResponseEntity.ok("Delete Investment");
    }
}
