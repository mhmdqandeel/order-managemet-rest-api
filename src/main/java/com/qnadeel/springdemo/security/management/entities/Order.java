package com.qnadeel.springdemo.security.management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDER")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {


    @PrePersist
    public void prePersist() {
        orderDate = LocalDateTime.now();
        status = OrderStatus.CREATED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    @Column(name = "order_price")
    private BigDecimal totalAmount;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

}
