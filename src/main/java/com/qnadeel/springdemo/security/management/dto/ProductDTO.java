package com.qnadeel.springdemo.security.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String category;

    private String productName;

    private String productDescription;

    private BigDecimal productPrice;

}