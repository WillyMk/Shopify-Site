package com.example.productservice.address.dto;

import com.example.productservice.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private Long id;
    private String country;
    private String city;
    private String addressLine1;
    private String addressLine2;
    private User user;
    private String userName;
}
