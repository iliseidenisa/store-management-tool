package com.practice.store.service.interfaces;

import com.practice.store.dto.ProductDtoCreate;
import com.practice.store.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {
    List<ProductDto> findAllProducts();

    ProductDto updateProduct(ProductDto dto);

    void delete(Long id);

    ProductDto findById(Long id);

    ProductDto addProduct(ProductDtoCreate dto);
}
