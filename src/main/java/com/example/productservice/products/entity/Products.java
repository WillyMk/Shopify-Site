package com.example.productservice.products.entity;

import com.example.productservice.inventory.entity.ShopifyInventory;
import com.example.productservice.products.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String shortDescription;
    private String longDescription;
    @Column(nullable = false)
    private String skuCode;
    @Column(nullable = false)
    private BigDecimal price;
    @OneToOne(mappedBy = "products", cascade = CascadeType.REMOVE, optional = false, orphanRemoval = true)
    private ShopifyInventory shopifyInventory;

    public ProductDto toData(){
        ProductDto p = new ProductDto();
        p.setName(this.getName());
        p.setShortDescription(this.getShortDescription());
        p.setLongDescription(this.getLongDescription());
        p.setId(this.getId());
        p.setPrice(this.getPrice());
        p.setSkuCode(this.getSkuCode());
        p.setSkuCode(this.getSkuCode());
        return p;
    }
}
