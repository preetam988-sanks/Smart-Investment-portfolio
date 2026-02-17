package com.smartinvestment.smart_investment_portfolio.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @GetMapping("/stats")
    public ResponseEntity<?> getStats() {
        return ResponseEntity.ok("Only admins can see this;System is running at 100% capacity");
    }
}
