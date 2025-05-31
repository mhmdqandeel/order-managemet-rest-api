package com.qnadeel.springdemo.security.management.controller;

import com.qnadeel.springdemo.security.management.dto.request.OrderRequest;
import com.qnadeel.springdemo.security.management.dto.response.OrderResponse;
import com.qnadeel.springdemo.security.management.mapper.OrderMapper;
import com.qnadeel.springdemo.security.management.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @PostMapping("/{userId}")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest
                                                , @PathVariable Long userId) {

        return ResponseEntity
                .ok(orderMapper
                        .orderToOrderResponse(orderService.createOrder(userId, orderRequest)));
    }


}
