package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductSizeEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "product_size_id", nullable = false)
    private Long productSizeId;

    public ProductSizeEntityKey setPk(Long productSizeId) {
        setProductSizeId(productSizeId);
        return this;
    }
}
