package com.example.productservice.order.service;

import com.example.productservice.address.entity.Address;
import com.example.productservice.address.service.AddressService;
import com.example.productservice.exception.APIException;
import com.example.productservice.order.dto.OrderQuantityDto;
import com.example.productservice.order.entity.OrderQuantity;
import com.example.productservice.order.entity.ShopifyOrders;
import com.example.productservice.order.repo.OrderQuantityRepo;
import com.example.productservice.order.repo.ShopifyOrderRepo;
import com.example.productservice.products.entity.Products;
import com.example.productservice.products.service.ProductsService;
import com.example.productservice.user.entity.User;
import com.example.productservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ShopifyOrderRepo shopifyOrderRepo;
    private final OrderQuantityRepo orderQuantityRepo;
    private final UserService userService;
    private final AddressService addressService;
    private ProductsService productsService;

    public Page<ShopifyOrders> getOrders(Pageable pageable, @AuthenticationPrincipal User user) {
        User userData = userService.getLoggedInUser(user);
        return shopifyOrderRepo.findByUser(userData, pageable);
    }

    @Transactional
    public void saveOrders(OrderQuantityDto order) {
        ShopifyOrders shopifyOrder = new ShopifyOrders();
        if(order.getAddressId() == null) {
            throw APIException.nullPointer("Please add address");
        }
        Address address = addressService.getAddress(order.getAddressId());
        shopifyOrder.setAddress(address);
        shopifyOrder.setUser(order.getUser());
        ShopifyOrders s = shopifyOrderRepo.save(shopifyOrder);

        for(Long id : order.getOrderId()){
            Products p = productsService.getProduct(id);
            OrderQuantity orderQuantity = new OrderQuantity();
            orderQuantity.setQuantity(order.getQuantity());
            orderQuantity.setShopifyOrders(s);
            orderQuantity.setProducts(p);
            orderQuantityRepo.save(orderQuantity);
        }
    }
}
