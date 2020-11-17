package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.CartItem;
import com.kodilla.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Override
    void deleteById(Long id);

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
