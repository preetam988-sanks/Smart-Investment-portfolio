package com.smartinvestment.smart_investment_portfolio.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import javax.swing.*;

public record LoginRequest(
        @Email
        @NotBlank String email,
        @NotBlank String password
) {

}
