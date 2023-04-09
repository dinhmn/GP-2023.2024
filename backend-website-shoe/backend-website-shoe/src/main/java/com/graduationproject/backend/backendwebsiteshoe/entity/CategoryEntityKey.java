package com.graduationproject.backend.backendwebsiteshoe.entity;

import java.io.Serializable;
import javax.persistence.Column;
import lombok.Data;

/**
 * Category entity key.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
public class CategoryEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "category_id", nullable = false)
  private Long categoryId;

  @Column(name = "trademark_id", nullable = false)
  private Long trademarkId;

  /**
   * Set PK.
   *
   * @param categoryId  categoryId
   * @param trademarkId trademarkId
   * @return key.
   */
  public CategoryEntityKey setPk(Long categoryId, Long trademarkId) {
    setCategoryId(categoryId);
    setTrademarkId(trademarkId);
    return this;
  }
}
