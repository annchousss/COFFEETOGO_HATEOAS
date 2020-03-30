package ru.itis.coffeeservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.coffeeservice.models.Category;
import ru.itis.coffeeservice.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category removeAllDeletedProducts(Long id) {
        Category c = categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        c.removeAllDeletedProducts();
        categoryRepository.save(c);
        return c;
    }
}
