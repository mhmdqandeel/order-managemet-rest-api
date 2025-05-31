package com.qnadeel.springdemo.security.management.mapper;

import com.qnadeel.springdemo.security.management.dto.response.OrderItemResponse;
import com.qnadeel.springdemo.security.management.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Builder
@Data
public class OrderItemMapper {

    public List<OrderItemResponse> toOrderItemListResponse(List<OrderItem> orderItem) {

        return orderItem.stream()
                .map(orderItem1 -> OrderItemResponse.builder()
                            .productName(orderItem1.getProduct().getProductName())
                            .quantity(orderItem1.getQuantity())
                            .price(orderItem1.getPrice())
                            .build())
                .toList();

    }
}
