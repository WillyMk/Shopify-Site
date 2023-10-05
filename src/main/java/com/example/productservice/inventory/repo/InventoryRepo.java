package com.example.productservice.inventory.repo;

import com.example.productservice.inventory.entity.ShopifyInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<ShopifyInventory, Long> {
}
