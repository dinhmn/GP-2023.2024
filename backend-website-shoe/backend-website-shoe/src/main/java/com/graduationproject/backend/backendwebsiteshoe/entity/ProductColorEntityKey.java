package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductColorEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "product_color_id", nullable = false)
    private Long productColorId;

    public ProductColorEntityKey setPk(Long productColorId) {
        setProductColorId(productColorId);
        return this;
    }
}
