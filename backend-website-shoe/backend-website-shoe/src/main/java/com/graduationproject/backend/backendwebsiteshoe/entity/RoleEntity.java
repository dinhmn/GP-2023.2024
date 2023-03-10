package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(RoleEntityKey.class)
@Table(name = "tbl_role")
public class RoleEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public RoleEntity() {

        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_name", nullable = false)
    private String role_name;

    @Column(name = "role_description", nullable = false)
    private String role_description;

}
