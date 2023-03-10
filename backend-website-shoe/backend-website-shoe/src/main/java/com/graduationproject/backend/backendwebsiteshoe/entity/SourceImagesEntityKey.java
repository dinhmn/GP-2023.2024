package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class SourceImagesEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "image_id", nullable = false)
    private Long imageId;

    public SourceImagesEntityKey setPk(Long productId, Long imageId) {
        setProductId(productId);
        setImageId(imageId);
        return this;
    }
}
