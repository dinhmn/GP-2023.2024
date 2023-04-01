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
 * Password entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(PasswordEntityKey.class)
@Table(name = "tbl_password")
public class PasswordEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public PasswordEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "password_id", nullable = false)
  private Long passwordId;

  @Id
  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "old_password")
  private String oldPassword;

}
