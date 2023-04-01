package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(UserEntityKey.class)
@Table(name = "tbl_user")
public class UserEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public UserEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Id
  @Column(name = "role_id", nullable = false)
  private Long roleId;

  @Column(name = "profile_id")
  private Long profileId;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "user_password", nullable = false)
  private String userPassword;

}
