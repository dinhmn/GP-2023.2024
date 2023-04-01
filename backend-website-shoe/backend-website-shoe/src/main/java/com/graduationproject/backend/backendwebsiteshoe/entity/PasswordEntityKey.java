package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;

/**
 * Password entity key.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
public class PasswordEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "password_id", nullable = false)
  private Long passwordId;

  /**
   * Set PK.
   *
   * @param passwordId passwordId
   * @return key.
   */
  public PasswordEntityKey setPk(Long passwordId) {
    setPasswordId(passwordId);
    return this;
  }
}
