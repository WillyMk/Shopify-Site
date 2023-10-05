package com.example.productservice.inventory.service;

import com.example.productservice.inventory.dto.InventoryDto;
import com.example.productservice.inventory.entity.ShopifyInventory;
import com.example.productservice.inventory.repo.InventoryRepo;
import com.example.productservice.products.entity.Products;
import com.example.productservice.products.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final ProductsService productsService;
    private final InventoryRepo inventoryRepo;
    public ShopifyInventory saveInventory(InventoryDto inventory) {
        ShopifyInventory i = new ShopifyInventory();
        i.setQuantity(inventory.getQuantity());
        Products product = productsService.getProduct(inventory.getProductId());
        i.setProducts(product);
        return inventoryRepo.save(i);
    }

    public Page<ShopifyInventory> getInventories(Pageable pageable) {
        return inventoryRepo.findAll(pageable);
    }
}
