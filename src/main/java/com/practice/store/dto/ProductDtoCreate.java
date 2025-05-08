package com.practice.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoCreate {
    private String name;

    private String category;

    private double price;

    private int stock;

    private String description;
}
