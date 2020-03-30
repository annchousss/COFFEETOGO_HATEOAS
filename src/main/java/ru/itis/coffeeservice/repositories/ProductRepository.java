package ru.itis.coffeeservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.coffeeservice.models.Product;

import java.awt.print.Pageable;
import java.util.List;

@RepositoryRestResource
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
