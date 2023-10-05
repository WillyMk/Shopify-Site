package com.example.productservice.products.repo;

import com.example.productservice.products.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {
    Optional<Products> findBySkuCode(String skuCode);
}
