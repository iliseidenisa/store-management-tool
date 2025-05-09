package com.practice.store.controller;

import com.practice.store.dto.ProductDtoCreate;
import com.practice.store.dto.ProductDto;
import com.practice.store.service.interfaces.ProductService;
import com.practice.store.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-management")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping()
    public ResponseEntity addProduct(@RequestBody ProductDtoCreate dto) {
        return ResponseEntity.ok().body(ResponseUtil.success(HttpStatus.OK, productService.addProduct(dto)));
    }


    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping()
    public ResponseEntity getProducts() {
        return ResponseEntity.ok().body(ResponseUtil.success(HttpStatus.OK, productService.findAllProducts()));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity findProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(ResponseUtil.success(HttpStatus.OK, productService.findById(id)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PutMapping()
    public ResponseEntity updateProduct(@RequestBody ProductDto dto) {
        return ResponseEntity.ok().body(ResponseUtil.success(HttpStatus.OK, productService.updateProduct(dto)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PutMapping("change-price")
    public ResponseEntity changePrice(@RequestBody ProductDto dto) {
        return ResponseEntity.ok().body(ResponseUtil.success(HttpStatus.OK, productService.changePrice(dto)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
