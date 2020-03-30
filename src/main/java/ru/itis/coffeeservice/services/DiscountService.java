package ru.itis.coffeeservice.services;

import ru.itis.coffeeservice.models.Discount;

public interface DiscountService {
    Discount extendCard(Long id);
}
