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
public class ProductSizeEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "product_size_id", nullable = false)
  private Long productSizeId;

  /**
   * Set PK.
   *
   * @param productSizeId productSizeId
   * @return key.
   */
  public ProductSizeEntityKey setPk(Long productSizeId) {
    setProductSizeId(productSizeId);
    return this;
  }
}
