package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    public ProductEntityKey setPk(Long productId, Long categoryId) {
        setProductId(productId);
        setCategoryId(categoryId);
        return this;
    }
}
