package com.example.productservice.address.controller;

import com.example.productservice.address.dto.AddressDto;
import com.example.productservice.address.entity.Address;
import com.example.productservice.address.service.AddressService;
import com.example.productservice.inventory.dto.InventoryDto;
import com.example.productservice.inventory.entity.ShopifyInventory;
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
@RequestMapping("api")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    @PostMapping("/address")
    public ResponseEntity<?> saveAddress(@RequestBody AddressDto addressDto, @AuthenticationPrincipal User user){
        addressDto.setUser(user);
        AddressDto address = addressService.saveAddress(addressDto).toData();
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @GetMapping("/address")
    public ResponseEntity<?> getInventory(@RequestParam(name = "page", defaultValue = "1") int page,
                                          @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Address> addresses = addressService.getAddresses(pageable);
        List<AddressDto> addressesList = addresses.stream().map(Address::toData).toList();
        Pagination<List<?>> content = new Pagination<>();
        content.setContent(addressesList);
        content.setPage(addresses.getNumber() + 1);
        content.setPageSize(addresses.getSize());
        content.setTotalElements(addresses.getTotalElements());
        return ResponseEntity.status(HttpStatus.OK).body(content);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<?> getAddress(@PathVariable Long id){
        AddressDto address = addressService.getAddress(id).toData();
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }
}
