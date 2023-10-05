package com.example.productservice.order.dto;

import com.example.productservice.order.entity.OrderQuantity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShopifyOrderDto {
    private Long id;
    private String country;
    private String city;
    private String addressLine1;
    private String addressLine2;
    private List<OrderQuantity> orderQuantities;
}
