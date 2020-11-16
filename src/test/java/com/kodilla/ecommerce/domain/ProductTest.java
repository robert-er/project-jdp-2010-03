package com.kodilla.ecommerce.domain;
import com.kodilla.ecommerce.repository.GroupRepository;
import com.kodilla.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    private final Product product = new Product("Test Product", new BigDecimal(300), "Test Description", 40L);
    private final Product product1 = new Product("Second Test Product", new BigDecimal(500), "Test Description", 70L);
    private final Group group = new Group();

    @Test
    public void createProductTest() {

        //Given

        //When
        product.setGroup(group);
        productRepository.save(product);
        Long productLong = product.getId();

        //Then
        assertTrue(productRepository.findById(productLong).isPresent());

        //CleanUp
        productRepository.deleteById(productLong);

    }

    @Test
    public void AddProductToGroupTest() {

        //Given
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);

        //When
        group.setProducts(productList);
        product.setGroup(group);
        product1.setGroup(group);
        productRepository.save(product);
        productRepository.save(product1);
        groupRepository.save(group);
        Long productLong = product.getId();

        //Then
        assertTrue(group.getProducts().contains(product));
        assertTrue(group.getProducts().contains(product1));

        //CleanUp
        productRepository.deleteById(productLong);

    }
}