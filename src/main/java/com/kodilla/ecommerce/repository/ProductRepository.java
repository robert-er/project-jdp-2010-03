package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Long id);

    @Override
    <S extends Product> S save(S product);

    @Override
    void deleteById(Long id);

    @Override
    boolean existsById(Long id);

}
