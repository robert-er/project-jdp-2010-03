package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByUserId(Long id);
}
