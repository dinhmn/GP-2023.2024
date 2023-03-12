package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "trademark_id", nullable = false)
    private Long trademarkId;

    public CategoryEntityKey setPk(Long categoryId, Long trademarkId) {
        setCategoryId(categoryId);
        setTrademarkId(trademarkId);
        return this;
    }
}
