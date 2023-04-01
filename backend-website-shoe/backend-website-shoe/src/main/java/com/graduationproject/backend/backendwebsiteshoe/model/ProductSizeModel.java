package com.graduationproject.backend.backendwebsiteshoe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model product size.
 *
 * @author Mai Ngoc Dinh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSizeModel {

  private Long productSizeId;

  private String productSizeName;

  private Integer productSizeQuantity;
}
