package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    public OrderEntityKey setPk(Long orderId, Long userId) {
        setOrderId(orderId);
        setUserId(userId);
        return this;
    }
}
