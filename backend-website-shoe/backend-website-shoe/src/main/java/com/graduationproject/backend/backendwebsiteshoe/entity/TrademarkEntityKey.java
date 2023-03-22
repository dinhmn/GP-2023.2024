package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class TrademarkEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "trademark_id", nullable = false)
    private Long trademarkId;

    public TrademarkEntityKey setPk(Long trademarkId) {
        setTrademarkId(trademarkId);
        return this;
    }
}
