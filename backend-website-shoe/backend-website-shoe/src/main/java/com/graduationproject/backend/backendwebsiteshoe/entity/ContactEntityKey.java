package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class ContactEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "contact_id", nullable = false)
    private Long contactId;

    public ContactEntityKey setPk(Long productId, Long contactId) {
        setProductId(productId);
        setContactId(contactId);
        return this;
    }
}
