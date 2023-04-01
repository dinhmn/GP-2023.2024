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
 * Category entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(CategoryEntityKey.class)
@Table(name = "tbl_category")
public class CategoryEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public CategoryEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id", nullable = false)
  private Long categoryId;

  @Id
  @Column(name = "trademark_id", nullable = false)
  private Long trademarkId;

  @Column(name = "category_name", nullable = false)
  private String categoryName;

  @Column(name = "category_description", length = 3000, nullable = false)
  private String categoryDescription;

  @Column(name = "seo", nullable = false)
  private String seo;

}
