package com.example.productservice.address.service;

import com.example.productservice.address.dto.AddressDto;
import com.example.productservice.address.entity.Address;
import com.example.productservice.address.repo.AddressRepo;
import com.example.productservice.exception.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {
    private final AddressRepo addressRepo;
    public Address saveAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setId(addressDto.getId());
        address.setAddressLine2(addressDto.getAddressLine2());
        address.setAddressLine1(addressDto.getAddressLine1());
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setUser(addressDto.getUser());
        return addressRepo.save(address);
    }

    public Page<Address> getAddresses(Pageable pageable) {
        return addressRepo.findAll(pageable);
    }

    public Address getAddress(Long id) {
        return addressRepo.findById(id).orElseThrow(() -> APIException.notFound("Address with id {0} not found", id));
    }
}
