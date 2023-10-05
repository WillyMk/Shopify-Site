package com.example.productservice.order.controller;

import com.example.productservice.order.dto.OrderQuantityDto;
import com.example.productservice.order.dto.ShopifyOrderDto;
import com.example.productservice.order.entity.OrderQuantity;
import com.example.productservice.order.entity.ShopifyOrders;
import com.example.productservice.order.service.OrderService;
import com.example.productservice.user.entity.User;
import com.example.productservice.utility.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShopifyController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<?>  getOrders(@RequestParam(name = "page", defaultValue = "1") int page,
                                         @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                         @AuthenticationPrincipal User user){
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<ShopifyOrders> orders = orderService.getOrders(pageable, user);
        List<ShopifyOrderDto> orderList = orders.stream().map(ShopifyOrders::toData).toList();
        Pagination<List<?>> content = new Pagination<>();
        content.setContent(orderList);
        content.setPage(orders.getNumber() + 1);
        content.setPageSize(orders.getSize());
        content.setTotalElements(orders.getTotalElements());
        return ResponseEntity.status(HttpStatus.OK).body(content);
    }

    @PostMapping("/orders")
    public ResponseEntity<String> saveOrder(@RequestBody OrderQuantityDto order, @AuthenticationPrincipal User user){
        order.setUser(user);
        orderService.saveOrders(order);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order Created Successfully");
    }
}
