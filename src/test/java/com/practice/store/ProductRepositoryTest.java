package com.practice.store;

import com.practice.store.model.Product;
import com.practice.store.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    @Transactional
    @Rollback
    public void testSaveProduct() {
        String name = "Shirt";
        String description  = "Description product";
        double price = 2.4;
        int stock = 34;

        // Create a Product object with the test data
        Product product = Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .stock(stock)
                .build();

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct);
        assertNotNull(savedProduct.getId());

        assertEquals(name, savedProduct.getName());
        assertEquals(description, savedProduct.getDescription());
        assertEquals(stock, savedProduct.getStock());
        assertEquals(price, savedProduct.getPrice());
    }

    @Test
    @Transactional
    @Rollback
    public void testFindProductByNameNotFound() {
        String name = "Shoes6";
        Optional<Product> foundProduct = productRepository.findByName(name);

        assertFalse(foundProduct.isPresent());
    }
}
