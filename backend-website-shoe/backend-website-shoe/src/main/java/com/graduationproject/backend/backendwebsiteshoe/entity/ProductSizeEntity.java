package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
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
@IdClass(ProductSizeEntityKey.class)
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
