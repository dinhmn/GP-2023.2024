package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;

/**
 * Product color entity key.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
public class ProductColorEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "product_color_id", nullable = false)
  private Long productColorId;

  /**
   * Set PK.
   *
   * @param productColorId productColorId
   * @return key.
   */
  public ProductColorEntityKey setPk(Long productColorId) {
    setProductColorId(productColorId);
    return this;
  }
}
