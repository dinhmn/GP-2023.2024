package com.graduationproject.backend.backendwebsiteshoe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model product color.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductColorModel {

  private Long productColorId;

  private String productColorName;

  private Integer productColorQuantity;
}
