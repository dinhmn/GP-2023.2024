package com.graduationproject.backend.backendwebsiteshoe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model article.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleModel {

  private Long articleId;

  private String articleName;

  private String articleDescription;

  private Long productId;

}
