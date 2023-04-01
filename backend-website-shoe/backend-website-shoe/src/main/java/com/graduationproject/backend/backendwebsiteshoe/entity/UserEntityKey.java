package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;

/**
 * Cart entity key.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
public class UserEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  /**
   * Set PK.
   *
   * @param userId userId
   * @return key.
   */
  public UserEntityKey setPk(Long userId) {
    setUserId(userId);
    return this;
  }
}
