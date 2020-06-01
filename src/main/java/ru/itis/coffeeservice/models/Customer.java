package ru.itis.coffeeservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    @OneToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @OneToOne
    @JoinColumn(name = "login_id")
    private Login login;

    @OneToMany(mappedBy = "customer")
    private List<OrderItem> orders;


}
