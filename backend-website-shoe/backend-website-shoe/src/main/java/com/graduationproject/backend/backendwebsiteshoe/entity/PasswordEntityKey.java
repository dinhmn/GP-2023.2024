package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class PasswordEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "password_id", nullable = false)
    private Long passwordId;

    public PasswordEntityKey setPk(Long passwordId) {
        setPasswordId(passwordId);
        return this;
    }
}
