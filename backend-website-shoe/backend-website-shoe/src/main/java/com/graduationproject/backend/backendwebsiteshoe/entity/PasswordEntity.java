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
 * Password entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
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
