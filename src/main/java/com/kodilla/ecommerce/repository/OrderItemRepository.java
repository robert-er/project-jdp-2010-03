package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
