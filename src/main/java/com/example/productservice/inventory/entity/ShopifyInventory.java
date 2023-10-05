package com.example.productservice.inventory.entity;

import com.example.productservice.inventory.dto.InventoryDto;
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
public class ShopifyInventory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "products_id", nullable = false, unique = true)
    private Products products;

    @Column(nullable = false)
    private Integer quantity;

    public InventoryDto toData() {
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setId(this.getId());
        inventoryDto.setName(this.getProducts().getName());
        inventoryDto.setPrice(this.getProducts().getPrice());
        inventoryDto.setShortDescription(this.getProducts().getShortDescription());
        inventoryDto.setQuantity(this.getQuantity());
        return inventoryDto;
    }
}
