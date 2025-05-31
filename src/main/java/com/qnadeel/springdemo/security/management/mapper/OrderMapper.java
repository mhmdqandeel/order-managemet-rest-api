package com.qnadeel.springdemo.security.management.mapper;

import com.qnadeel.springdemo.security.management.dto.response.OrderResponse;
import com.qnadeel.springdemo.security.management.entities.Order;
import com.qnadeel.springdemo.security.management.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Builder
@Data
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderResponse orderToOrderResponse(Order order) {

        return OrderResponse.builder()
                .userId(order.getUser().getUserId())
                .orderId(order.getOrderId())
                .items(orderItemMapper.toOrderItemListResponse(order.getItems()))
                .totalAmount(order.getTotalAmount())
                .build();
    }
}
