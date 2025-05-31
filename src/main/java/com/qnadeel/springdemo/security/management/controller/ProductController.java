package com.qnadeel.springdemo.security.management.controller;

import com.qnadeel.springdemo.security.management.dto.ProductDTO;
import com.qnadeel.springdemo.security.management.mapper.ProductMapper;
import com.qnadeel.springdemo.security.management.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @PostMapping("/")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO ProductDTO) {
        productService.saveProduct(ProductDTO);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity
                .ok(productMapper
                        .listOfProductsToListOfDTOProducts(productService
                                .getAllProducts()));
    }

    @GetMapping("/by-product-name")
    public ResponseEntity<ProductDTO> getProductByName(@RequestParam String productName) {
        return ResponseEntity
                .ok(productMapper
                        .ProductToDTOProduct(productService
                                .getProductByName(productName)));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        return ResponseEntity
                .ok(productMapper
                        .ProductToDTOProduct(productService
                                .getProductById(productId)));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO ProductDTO) {
        return ResponseEntity
                .ok(productMapper
                        .ProductToDTOProduct(productService
                                .updateProduct(productId, ProductDTO)));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping("/product-by-category/{categoryName}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String categoryName) {
        return ResponseEntity
                .ok(productMapper
                        .listOfProductsToListOfDTOProducts(productService
                                .getProductsByCategoryName(categoryName)));
    }
}