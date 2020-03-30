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
public class Discount {
    @Id
    @GeneratedValue
    private Long id;

    private int ordersNumber;
    private int discountPercent;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private DiscountStatus status;

    public void extendCard() {
        if(this.status.equals(DiscountStatus.VALID)) {
            throw new IllegalArgumentException();
        } else if (this.status.equals(DiscountStatus.EXPIRED)) {
            this.status = DiscountStatus.VALID;
        }
    }

}
