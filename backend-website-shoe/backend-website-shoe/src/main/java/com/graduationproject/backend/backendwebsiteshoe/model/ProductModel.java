package com.graduationproject.backend.backendwebsiteshoe.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create product model.
 *
 * @author Mai Ngoc Dinh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel {

  private Long categoryId;

  private Long productId;

  private String productName;

  private String productDescription;

  private String productPrice;

  private String productPriceSale;

  private Integer quantity;

  private String status;

  private List<SourceImageModel> sourceImageModelList;

  private List<ProductColorModel> productColorModelList;

  private List<ProductSizeModel> productSizeModelList;

}
