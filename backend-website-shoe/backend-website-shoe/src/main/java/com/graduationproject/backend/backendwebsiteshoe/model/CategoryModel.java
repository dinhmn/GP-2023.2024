package com.graduationproject.backend.backendwebsiteshoe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model category.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {

  private Long categoryId;

  private Long trademarkId;

  private String trademarkName;

  private String categoryDescription;

  private String categoryName;

  private String categorySeo;

  private String createdDate;

  private String updatedDate;

  private String categoryStatus;
}
