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
 * Product color entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tbl_product_color")
public class ProductColorEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public ProductColorEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_color_id", nullable = false)
  private Long productColorId;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "product_color_name", nullable = false)
  private String productColorName;

  @Column(name = "product_color_quantity")
  private Integer productColorQuantity;
}
