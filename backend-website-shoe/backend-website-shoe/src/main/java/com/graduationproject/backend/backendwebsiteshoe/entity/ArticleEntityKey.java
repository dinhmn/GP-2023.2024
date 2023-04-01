package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;

/**
 * Article entity key.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
public class ArticleEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "article_id", nullable = false)
  private Long articleId;

  /**
   * Set PK.
   *
   * @param productId productId
   * @param articleId articleId
   * @return key.
   */
  public ArticleEntityKey setPk(Long productId, Long articleId) {
    setProductId(productId);
    setArticleId(articleId);
    return this;
  }
}
