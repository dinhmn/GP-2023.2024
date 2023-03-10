package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(PasswordEntityKey.class)
public class PasswordEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public PasswordEntity() {

        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id", nullable = false)
    private Long profileId;

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "old_password")
    private String oldPassword;

}
