package com.qnadeel.springdemo.security.management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long userId;

    private Long orderId;

    private List<OrderItemResponse> items;

    private BigDecimal totalAmount;
}
