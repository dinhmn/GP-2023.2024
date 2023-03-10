package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(CartEntityKey.class)
public class CartEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public CartEntity() {

        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Long cartId;

    @Id
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_quantity", nullable = false)
    private String productQuantity;

    @Column(name = "product_current_price", nullable = false)
    private String productCurrentPrice;
}
