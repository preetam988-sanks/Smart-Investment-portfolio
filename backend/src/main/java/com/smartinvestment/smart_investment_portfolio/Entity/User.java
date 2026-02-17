package com.smartinvestment.smart_investment_portfolio.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.smartinvestment.smart_investment_portfolio.Enums.Role;
import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     private String name;
     @Column(unique=true,nullable=false)
    private String email;
     @JsonIgnore
     private String password;
     @Enumerated(EnumType.STRING)
    private Role role;
     @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,orphanRemoval = true)
     @JsonManagedReference
    private List<Investment> investments;
}
