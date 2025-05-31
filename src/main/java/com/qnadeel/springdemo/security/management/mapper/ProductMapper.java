package com.qnadeel.springdemo.security.management.mapper;

import com.qnadeel.springdemo.security.management.dto.ProductDTO;
import com.qnadeel.springdemo.security.management.entities.Product;
import com.qnadeel.springdemo.security.management.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Builder
@Data
public class ProductMapper {

    private final CategoryService categoryService;

    public Product ProductDTOToProduct(ProductDTO ProductDTO) {
        return Product.builder()
                .productName(ProductDTO.getProductName())
                .productDescription(ProductDTO.getProductDescription())
                .productPrice(ProductDTO.getProductPrice())
                .category(categoryService.getCategoryByName(ProductDTO.getCategory()))
                .build();
    }

    public ProductDTO ProductToDTOProduct(Product product) {
        return ProductDTO.builder()
                .category(product.getCategory().getCategoryName())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPrice())
                .build();
    }

    public List<ProductDTO> listOfProductsToListOfDTOProducts(List<Product> productList) {
        return productList.stream()
                .map(product -> ProductDTO.builder()
                        .category(product.getCategory().getCategoryName())
                        .productName(product.getProductName())
                        .productDescription(product.getProductDescription())
                        .productPrice(product.getProductPrice())
                        .build())
                .toList();
    }
}