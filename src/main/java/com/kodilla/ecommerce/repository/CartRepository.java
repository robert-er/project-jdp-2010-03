package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Override
    <S extends Cart> S save(S cart);

    @Override
    void deleteById(Long id);

    @Override
    List<Cart> findAll();

    @Override
    Optional<Cart> findById(Long id);

    Optional<Cart> findByUserId(Long userId);
}
