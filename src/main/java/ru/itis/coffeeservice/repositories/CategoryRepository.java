package ru.itis.coffeeservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.coffeeservice.models.Category;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
