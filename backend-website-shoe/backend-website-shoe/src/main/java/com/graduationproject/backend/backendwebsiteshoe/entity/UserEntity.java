package com.graduationproject.backend.backendwebsiteshoe.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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

  @Column(name = "role_id", nullable = false)
  private Long roleId;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "user_password", nullable = false)
  private String userPassword;

  @Column(name = "user_email", length = 80, nullable = false)
  private String userEmail;

}
