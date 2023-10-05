package com.example.productservice.address.entity;

import com.example.productservice.address.dto.AddressDto;
import com.example.productservice.user.entity.User;
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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String city;
    private String addressLine1;
    private String addressLine2;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public AddressDto toData() {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(this.getId());
        addressDto.setCountry(this.getCountry());
        addressDto.setAddressLine1(this.getAddressLine1());
        addressDto.setAddressLine2(this.getAddressLine2());
        if (this.getUser() != null) {
            addressDto.setUserName(this.getUser().getUsername());
        }
        return addressDto;
    }

}
