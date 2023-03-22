package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class ContactEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "contact_id", nullable = false)
    private Long contactId;

    public ContactEntityKey setPk(Long contactId) {
        setContactId(contactId);
        return this;
    }
}
