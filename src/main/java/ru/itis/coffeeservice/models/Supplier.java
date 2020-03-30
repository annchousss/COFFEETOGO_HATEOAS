package ru.itis.coffeeservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supplier {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String otherDetails;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
}
