package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(RoleEntityKey.class)
public class RoleEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public RoleEntity() {

        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long role_id;

    @Id
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "role_name", nullable = false)
    private String role_name;

    @Column(name = "role_description", nullable = false)
    private String role_description;

}
