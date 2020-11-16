package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Override
    List<Order> findAll();

    @Override
    Optional<Order> findById(Long orderId);

    @Override
    void deleteById(Long orderId);

    Optional<Order> findByUserId(Long id);

    @Override
    boolean existsById(Long orderId);
}
