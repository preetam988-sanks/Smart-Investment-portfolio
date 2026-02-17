package com.smartinvestment.smart_investment_portfolio.Controller;

import com.smartinvestment.smart_investment_portfolio.DTO.AuthResponse;
import com.smartinvestment.smart_investment_portfolio.DTO.LoginRequest;
import com.smartinvestment.smart_investment_portfolio.DTO.RegisterRequest;
import com.smartinvestment.smart_investment_portfolio.Service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthenticationService authenticationService;
  @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest registerRequest ){
      return ResponseEntity.ok(authenticationService.register(registerRequest));
  }
  @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest ){
      return ResponseEntity.ok(authenticationService.login(loginRequest));
  }

}
