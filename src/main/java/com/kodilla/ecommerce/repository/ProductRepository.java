package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
