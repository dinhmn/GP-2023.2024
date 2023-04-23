package com.graduationproject.backend.backendwebsiteshoe.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Filter product.
 *
 * @author Mai Ngoc Dinh
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class FilterProduct {

  private int pageSize;

  private int pageNo;

  private String sortDirection;

  private String sortBy;

  private String searchValue;

  private Long categoryId;

  private BigDecimal priceMin;

  private BigDecimal priceMax;

  private List<String> sizeList;

  private List<String> colorList;
}
