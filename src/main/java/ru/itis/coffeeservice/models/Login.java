package ru.itis.coffeeservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Login {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
