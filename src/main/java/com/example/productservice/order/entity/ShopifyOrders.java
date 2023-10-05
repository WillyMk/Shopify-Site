package com.example.productservice.order.entity;

import com.example.productservice.address.entity.Address;
import com.example.productservice.order.dto.ShopifyOrderDto;
import com.example.productservice.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopifyOrders {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "shopifyOrders", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderQuantity> orderQuantities = new ArrayList<>();

    public ShopifyOrderDto toData(){
        ShopifyOrderDto dto = new ShopifyOrderDto();
        dto.setId(this.getId());
        if (this.getAddress() != null) {
            dto.setCity(this.getAddress().getCity());
            dto.setCountry(this.getAddress().getCountry());
            dto.setAddressLine1(this.getAddress().getAddressLine1());
            dto.setAddressLine2(this.getAddress().getAddressLine2());
        }
        dto.setOrderQuantities(this.getOrderQuantities());
        return dto;
    }

}
