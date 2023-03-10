package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class TrademarkEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "trademark_id", nullable = false)
    private Long trademarkId;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    public TrademarkEntityKey setPk(Long trademarkId, Long categoryId) {
        setTrademarkId(trademarkId);
        setCategoryId(categoryId);
        return this;
    }
}
