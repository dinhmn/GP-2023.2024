package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;

/**
 * Role entity key.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
public class RoleEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "role_id", nullable = false)
  private Long roleId;

  /**
   * Set PK.
   *
   * @param roleId roleId
   * @return key.
   */
  public RoleEntityKey setPk(Long roleId) {
    setRoleId(roleId);
    return this;
  }
}
