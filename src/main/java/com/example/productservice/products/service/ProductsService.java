package com.example.productservice.products.service;

import com.example.productservice.exception.APIException;
import com.example.productservice.products.dto.ProductDto;
import com.example.productservice.products.entity.Products;
import com.example.productservice.products.repo.ProductsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductsService {
    private final ProductsRepo productsRepo;

    public Products saveProduct(ProductDto product) {
        boolean available = findProductBySkuCode(product.getSkuCode());
        if(available){
            throw new RuntimeException("Product already available");
        }
        Products p = new Products();
        p.setName(product.getName());
        p.setShortDescription(product.getShortDescription());
        p.setLongDescription(product.getLongDescription());
        p.setPrice(product.getPrice());
        p.setSkuCode(product.getSkuCode());
        return productsRepo.save(p);
    }

    public Page<Products> getProducts(Pageable pageable) {
        return productsRepo.findAll(pageable);
    }

    public boolean findProductBySkuCode(String skuCode) {
        Optional<Products> product = productsRepo.findBySkuCode(skuCode);
        return product.isPresent();
    }

    public Products getProduct(Long id) {
        System.out.println("id = " + id);
        System.out.println("productsRepo.findById(id) = " + productsRepo.findById(id));
        return productsRepo.findById(id).orElseThrow(() -> APIException.notFound("Product with id {0} not found", id));
    }
}
