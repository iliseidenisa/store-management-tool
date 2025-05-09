package com.practice.store;

import com.practice.store.dto.ProductDto;
import com.practice.store.repository.ProductRepository;
import com.practice.store.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;
    @Test
    void shouldReturnAllProducts() throws Exception {
        List<ProductDto> products = List.of(
                new ProductDto(1L, "Shoes1", "Shoes", 10.0, 23, "Description"),
                new ProductDto(2L, "Socks", "Clothes", 20.0, 24, "Description")
        );

        when(productService.findAllProducts()).thenReturn(products);

        MvcResult result = mockMvc.perform(get("/api/product-management"))
                .andExpect(status().isOk()) // Expect status 200 OK
                .andReturn();

        // Get response content as a string
        String responseContent = result.getResponse().getContentAsString();

        assertTrue(responseContent.contains("Shoes1"));
        assertTrue(responseContent.contains("Socks"));
        assertTrue(true);

    }
}
