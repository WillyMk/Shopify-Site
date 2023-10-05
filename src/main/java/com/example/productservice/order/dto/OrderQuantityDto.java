package com.example.productservice.order.dto;

import com.example.productservice.address.entity.Address;
import com.example.productservice.order.entity.ShopifyOrders;
import com.example.productservice.products.entity.Products;
import com.example.productservice.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderQuantityDto {
    private Long id;
    private Products product;
    private String productName;
    private Integer quantity;
    private ShopifyOrders shopifyOrders;
    private List<Long> orderId;
    private Long addressId;
    private User user;
}
