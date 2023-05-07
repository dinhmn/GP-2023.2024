package com.graduationproject.backend.backendwebsiteshoe.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Product entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(ProductEntityKey.class)
@Table(name = "tbl_product")
public class ProductEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public ProductEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Id
  @Column(name = "category_id", nullable = false)
  private Long categoryId;

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "product_description")
  private String productDescription;

  @Column(name = "product_price", length = 20, nullable = false)
  private BigDecimal productPrice;

  @Column(name = "product_price_sale", length = 20)
  private BigDecimal productPriceSale;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @Column(name = "product_seo")
  private String productSeo;

}
