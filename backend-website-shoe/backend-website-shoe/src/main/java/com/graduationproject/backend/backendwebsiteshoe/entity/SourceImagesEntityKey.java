package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;

/**
 * Source images entity key.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
public class SourceImagesEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "image_id", nullable = false)
  private Long imageId;

  /**
   * Set PK.
   *
   * @param productId productId
   * @param imageId   imageId
   * @return key.
   */
  public SourceImagesEntityKey setPk(Long productId, Long imageId) {
    setProductId(productId);
    setImageId(imageId);
    return this;
  }
}
