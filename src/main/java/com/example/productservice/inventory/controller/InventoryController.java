package com.example.productservice.inventory.controller;

import com.example.productservice.inventory.dto.InventoryDto;
import com.example.productservice.inventory.entity.ShopifyInventory;
import com.example.productservice.inventory.service.InventoryService;
import com.example.productservice.utility.Pagination;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/inventory")
    public ResponseEntity<?> saveInventory(@RequestBody InventoryDto inventory){
        InventoryDto inventoryDto = inventoryService.saveInventory(inventory).toData();
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryDto);
    }

    @GetMapping("/inventory")
    public ResponseEntity<?> getInventory(@RequestParam(name = "page", defaultValue = "1") int page,
                                          @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<ShopifyInventory> inventories = inventoryService.getInventories(pageable);
        List<InventoryDto> inventoryList = inventories.stream().map(ShopifyInventory::toData).toList();
        Pagination<List<?>> content = new Pagination<>();
        content.setContent(inventoryList);
        content.setPage(inventories.getNumber() + 1);
        content.setPageSize(inventories.getSize());
        content.setTotalElements(inventories.getTotalElements());
        return ResponseEntity.status(HttpStatus.OK).body(content);
    }
}
