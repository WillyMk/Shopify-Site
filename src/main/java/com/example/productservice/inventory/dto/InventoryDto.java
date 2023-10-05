package com.example.productservice.inventory.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InventoryDto {
    private Long id;
    private String name;
    private String shortDescription;
    private BigDecimal price;
    private Integer quantity;
    private Long productId;
}
