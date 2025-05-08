package com.practice.store.service;

import com.practice.store.dto.ProductDtoCreate;
import com.practice.store.dto.ProductDto;
import com.practice.store.exception.ResourceNotFoundException;
import com.practice.store.mapper.ProductMapper;
import com.practice.store.model.Product;
import com.practice.store.repository.ProductRepository;
import com.practice.store.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> findAllProducts() {
        return productRepository.findAll().stream().map(productMapper::convertToDto).toList();
    }

    @Override
    public ProductDto updateProduct(ProductDto dto) {
        Product updatedProduct = productRepository.findById(dto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Product Not Found"));

        updatedProduct.setName(dto.getName());
        updatedProduct.setDescription(dto.getDescription());
        updatedProduct.setPrice(dto.getPrice());
        updatedProduct.setStock(dto.getStock());
        return productMapper.convertToDto(productRepository.save(updatedProduct));
    }

    @Override
    public void delete(Long id) {
        productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product Not Found"));
       productRepository.deleteById(id);
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return productMapper.convertToDto(product);
    }

    @Override
    public ProductDto addProduct(ProductDtoCreate dto) {
        Product created = Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .build();
        return productMapper.convertToDto(productRepository.save(created));
    }
}
