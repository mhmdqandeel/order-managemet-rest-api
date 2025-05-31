package com.qnadeel.springdemo.security.management.service;

import com.qnadeel.springdemo.security.management.dto.ProductDTO;
import com.qnadeel.springdemo.security.management.entities.Category;
import com.qnadeel.springdemo.security.management.entities.Product;
import com.qnadeel.springdemo.security.management.exeption.ResourcesNotFoundException;
import com.qnadeel.springdemo.security.management.mapper.ProductMapper;
import com.qnadeel.springdemo.security.management.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    private final ProductMapper productMapper;

    @Transactional
    public void saveProduct(ProductDTO ProductDTO) {

        Category category = categoryService
                        .getCategoryByName(ProductDTO.getCategory());

        if (productRepository.existsByProductName(ProductDTO.getProductName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product is already exists");
        }

        category.getProducts().add(productMapper.ProductDTOToProduct(ProductDTO));

        productRepository.save(productMapper.ProductDTOToProduct(ProductDTO));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByName(String productName) {
        return productRepository.findByProductName(productName)
                .orElseThrow(() -> new ResourcesNotFoundException("Product not found"));
    }

    @Transactional
    public Product updateProduct(Long productId, ProductDTO productDTO) {
        Product product = getProductById(productId);

        product.setCategory(categoryService.getCategoryByName(productDTO.getCategory()));
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductPrice(productDTO.getProductPrice());

        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public Product getProductById(Long productId) {
        return productRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourcesNotFoundException("Product not found"));
    }

    public List<Product> getProductsByCategoryName(String categoryName) {

        Category category = categoryService.getCategoryByName(categoryName);

        return category.getProducts().stream().toList();
    }
}