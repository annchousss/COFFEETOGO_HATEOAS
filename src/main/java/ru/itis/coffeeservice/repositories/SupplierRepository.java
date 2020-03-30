package ru.itis.coffeeservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.coffeeservice.models.Supplier;

@RepositoryRestResource
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
