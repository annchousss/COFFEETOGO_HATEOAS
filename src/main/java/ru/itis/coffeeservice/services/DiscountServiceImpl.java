package ru.itis.coffeeservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.coffeeservice.models.Discount;
import ru.itis.coffeeservice.models.Product;
import ru.itis.coffeeservice.repositories.DiscountRepository;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    DiscountRepository discountRepository;

    @Override
    public Discount extendCard(Long id) {
        Discount d = discountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        d.extendCard();
        discountRepository.save(d);
        return d;
    }
}