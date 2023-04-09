package com.graduationproject.backend.backendwebsiteshoe.common;

/**
 * Define enum role personal.
 *
 * @author Mai Ngoc Dinh
 */
public enum Role {

  ROLE_ADMIN("0", "ADMIN"),

  ROLE_USER("1", "USER"),

  ROLE_MODERATOR("2", "MODERATOR");

  /* Code */
  private final String code;

  /* Value */
  private final String value;

  /**
   * Set constructor.
   *
   * @param code  code
   * @param value value
   */
  Role(String code, String value) {
    this.code = code;
    this.value = value;
  }
}
