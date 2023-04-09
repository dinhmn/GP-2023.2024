package com.graduationproject.backend.backendwebsiteshoe.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Product size entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tbl_product_size")
public class ProductSizeEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public ProductSizeEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_size_id", nullable = false)
  private Long productSizeId;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "product_size_name", nullable = false)
  private String productSizeName;

  @Column(name = "product_size_quantity")
  private Integer productSizeQuantity;
}
