package com.graduationproject.backend.backendwebsiteshoe.entity;

import java.io.Serializable;
import javax.persistence.Column;
import lombok.Data;

/**
 * Product entity key.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
public class ProductEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "category_id", nullable = false)
  private Long categoryId;

  /**
   * Set PK.
   *
   * @param productId  productId
   * @param categoryId categoryId
   * @return key.
   */
  public ProductEntityKey setPk(Long productId, Long categoryId) {
    setProductId(productId);
    setCategoryId(categoryId);
    return this;
  }
}
