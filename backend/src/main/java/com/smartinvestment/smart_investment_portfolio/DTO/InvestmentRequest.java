package com.smartinvestment.smart_investment_portfolio.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record InvestmentRequest (
    @NotBlank String assetName,
    @Positive Double quantity,
    @Positive Double price
){}


