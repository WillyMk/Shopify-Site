package com.example.productservice.order.repo;

import com.example.productservice.order.entity.OrderQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderQuantityRepo extends JpaRepository<OrderQuantity, Long> {
}
