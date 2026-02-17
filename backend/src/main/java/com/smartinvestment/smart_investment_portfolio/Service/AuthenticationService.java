package com.smartinvestment.smart_investment_portfolio.Service;

import com.smartinvestment.smart_investment_portfolio.DTO.AuthResponse;
import com.smartinvestment.smart_investment_portfolio.DTO.LoginRequest;
import com.smartinvestment.smart_investment_portfolio.DTO.RegisterRequest;
import com.smartinvestment.smart_investment_portfolio.Entity.User;
import com.smartinvestment.smart_investment_portfolio.Enums.Role;
import com.smartinvestment.smart_investment_portfolio.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }
        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(()->new RuntimeException("User Not Found"));
        String token=jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
                );
        return new AuthResponse(token);
    }
}
