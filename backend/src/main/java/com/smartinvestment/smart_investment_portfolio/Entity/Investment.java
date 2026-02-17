package com.smartinvestment.smart_investment_portfolio.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String assetName;
    @Column(nullable = false)
    private Double quantity;
    @Column(nullable = false)
    private Double buyPrice;
    private LocalDateTime purchaseDate;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="User_Id",nullable = false)
    @JsonIgnore
    private User user;
    @PrePersist
    public void onCreate() {
        this.purchaseDate=LocalDateTime.now();
    }
}
