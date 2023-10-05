package com.example.productservice.products.controller;

import com.example.productservice.inventory.dto.InventoryDto;
import com.example.productservice.products.dto.ProductDto;
import com.example.productservice.products.entity.Products;
import com.example.productservice.products.service.ProductsService;
import com.example.productservice.utility.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;

    @PostMapping("products")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto product){
        ProductDto productDto = productsService.saveProduct(product).toData();
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(@RequestParam(name = "page", defaultValue = "1") int page,
                                         @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Products> products = productsService.getProducts(pageable);
        List<ProductDto> productDtoList = products.stream().map(Products::toData).toList();
        Pagination<List<?>> content = new Pagination();
        content.setContent(productDtoList);
        content.setPage(products.getNumber() + 1);
        content.setPageSize(products.getSize());
        content.setTotalElements(products.getTotalElements());
        return ResponseEntity.status(HttpStatus.OK).body(content);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id){
        ProductDto product = productsService.getProduct(id).toData();
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("/product")
    public ResponseEntity<?> getProductBySkuCode(@RequestParam(name="skuCode") String skuCode){
        Boolean product = productsService.findProductBySkuCode(skuCode);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
