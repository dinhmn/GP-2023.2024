package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class CartEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "cart_id", nullable = false)
    private Long cartId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    public CartEntityKey setPk(Long cartId, Long productId) {
        setCartId(cartId);
        setProductId(productId);
        return this;
    }
}
