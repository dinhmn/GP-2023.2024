package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    public CategoryEntityKey setPk(Long categoryId) {
        setCategoryId(categoryId);
        return this;
    }
}
