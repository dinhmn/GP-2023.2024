package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class RatesEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "rate_id", nullable = false)
    private Long rateId;

    public RatesEntityKey setPk(Long rateId) {
        setRateId(rateId);
        return this;
    }
}
