package com.example.productservice.order.entity;

import com.example.productservice.order.dto.OrderQuantityDto;
import com.example.productservice.products.entity.Products;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "products_id", nullable = false)
    private Products products;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "shopify_orders_id", nullable = false)
    private ShopifyOrders shopifyOrders;

    public OrderQuantityDto toData() {
        OrderQuantityDto orderQuantityDto = new OrderQuantityDto();
        orderQuantityDto.setId(this.getId());
        orderQuantityDto.setQuantity(this.getQuantity());
        orderQuantityDto.setProductName(this.getProducts().getName());
        return orderQuantityDto;
    }

}
