package com.kodilla.ecommerce.domain;
import com.kodilla.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    private final Product product = new Product();
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
    public void shouldSetTitle() {

        //Given
        String title = "Product name";

        //When
        product.setGroup(group);
        product.setTitle(title);
        productRepository.save(product);
        Long productLong = product.getId();

        //Then
        assertEquals(product.getTitle(), title);

        //CleanUp
        productRepository.deleteById(productLong);

    }

    @Test
    public void shouldSetPrice() {

        //Given
        BigDecimal price = new BigDecimal(500);

        //When
        product.setGroup(group);
        product.setPrice(price);
        productRepository.save(product);
        Long productLong = product.getId();

        //Then
        assertEquals(product.getPrice(), price);

        //CleanUp
        productRepository.deleteById(productLong);

    }

    @Test
    public void shouldSetDescription() {

        //Given
        String description = "Product description";

        //When
        product.setGroup(group);
        product.setDescription(description);
        productRepository.save(product);
        Long productLong = product.getId();

        //Then
        assertEquals(product.getDescription(), description);

        //CleanUp
        productRepository.deleteById(productLong);

    }

    @Test
    public void shouldSetQuantityInStock() {

        //Given
        Long quantityInStock = 5L;

        //When
        product.setGroup(group);
        product.setQuantityInStock(quantityInStock);
        productRepository.save(product);
        Long productLong = product.getId();

        //Then
        assertEquals(product.getQuantityInStock(), quantityInStock);

        //CleanUp
        productRepository.deleteById(productLong);

    }

}
