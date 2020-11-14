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
        product.setGroup(group);

        //When
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
        product.setGroup(group);

        String title = "Product name";
        product.setTitle(title);
        productRepository.save(product);

        //When
        String returnedTitle = product.getTitle();

        //Then
        assertEquals(title, returnedTitle);

        //CleanUp
        productRepository.deleteById(product.getId());

    }

    @Test
    public void shouldSetPrice() {

        //Given
        product.setGroup(group);

        BigDecimal price = new BigDecimal(500);
        product.setPrice(price);
        productRepository.save(product);

        //When
        BigDecimal returnedPrice = product.getPrice();

        //Then
        assertEquals(price, returnedPrice);

        //CleanUp
        productRepository.deleteById(product.getId());

    }

    @Test
    public void shouldSetDescription() {

        //Given
        product.setGroup(group);

        String description = "Product description";
        product.setDescription(description);
        productRepository.save(product);

        //When
        String returnedDescription = product.getDescription();

        //Then
        assertEquals(description, returnedDescription);

        //CleanUp
        productRepository.deleteById(product.getId());

    }

    @Test
    public void shouldSetQuantityInStock() {

        //Given
        product.setGroup(group);

        Long quantityInStock = 5L;
        product.setQuantityInStock(quantityInStock);
        productRepository.save(product);

        //When
        Long returnedQuantityInStock = product.getQuantityInStock();

        //Then
        assertEquals(quantityInStock, returnedQuantityInStock);

        //CleanUp
        productRepository.deleteById(product.getId());

    }

}
