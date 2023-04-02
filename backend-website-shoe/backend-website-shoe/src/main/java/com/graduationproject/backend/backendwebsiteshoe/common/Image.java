package com.graduationproject.backend.backendwebsiteshoe.common;

import lombok.Getter;

/**
 * Define enum code image.
 *
 * @author Mai Ngoc Dinh
 */
@Getter
public enum Image {
  /*
   * ImageCode = 30 => Image user
   * ImageCode = 31 => Image main product
   * ImageCode = 32 => Image multiple product
   * ImageCode = 33 => Image product color
   * */
  IMAGE_USER("30", "image user"),

  IMAGE_MAIN_PRODUCT("31", "image user"),

  IMAGE_SECONDARY_PRODUCT("32", "image user"),

  IMAGE_COLOR("33", "update"),

  IMAGE_ARTICLE("34", "image article");

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
  Image(String code, String value) {
    this.code = code;
    this.value = value;
  }
}
