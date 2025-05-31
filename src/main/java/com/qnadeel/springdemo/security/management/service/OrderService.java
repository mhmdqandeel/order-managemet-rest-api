package com.qnadeel.springdemo.security.management.service;

import com.qnadeel.springdemo.security.management.dto.request.OrderRequest;
import com.qnadeel.springdemo.security.management.entities.Order;
import com.qnadeel.springdemo.security.management.entities.OrderItem;
import com.qnadeel.springdemo.security.management.entities.Product;
import com.qnadeel.springdemo.security.management.entities.User;
import com.qnadeel.springdemo.security.management.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;

    @Transactional
    public Order createOrder(Long userId, OrderRequest orderRequest) {

        Order order = new Order();
        User user = userService.getUserByID(userId);
        order.setUser(user);

        orderRequest.getItems().forEach(item -> {
            Product product = productService.getProductById(item.getProductId());

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setOrderItemPrice(product.getProductPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity())));

            orderItem.setOrder(order);
            order.getItems().add(orderItem);
        });

        BigDecimal totalAmount = order.getItems().stream()
                        .map(OrderItem::getOrderItemPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(totalAmount);

        user.getOrders().add(order);

        return orderRepository.save(order);
    }
}
