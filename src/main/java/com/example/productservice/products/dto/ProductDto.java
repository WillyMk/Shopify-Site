package com.example.productservice.products.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String skuCode;
    private BigDecimal price;
}
