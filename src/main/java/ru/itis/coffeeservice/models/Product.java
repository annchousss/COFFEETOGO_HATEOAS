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
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private float price;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "products")
    private List<OrderItem> orders;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    public void postProduct() {
        if(this.status.equals(ProductStatus.DELETED)) {
            throw new IllegalArgumentException();
        } else if (this.status.equals(ProductStatus.DRAFT)) {
            this.status = ProductStatus.PUBLISHED;
        }
    }


}
