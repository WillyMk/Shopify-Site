package com.example.productservice.order.repo;

import com.example.productservice.order.entity.ShopifyOrders;
import com.example.productservice.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopifyOrderRepo extends JpaRepository<ShopifyOrders, Long> {
    Page<ShopifyOrders> findByUser(User user, Pageable pageable);

}
