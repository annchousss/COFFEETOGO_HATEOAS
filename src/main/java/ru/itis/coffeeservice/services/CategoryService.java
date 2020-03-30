package ru.itis.coffeeservice.services;


import ru.itis.coffeeservice.models.Category;

public interface CategoryService {
    Category removeAllDeletedProducts(Long id);
}
