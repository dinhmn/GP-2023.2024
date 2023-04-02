package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Article entity key.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tbl_article")
public class ArticleEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public ArticleEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "article_id", nullable = false)
  private Long articleId;

  @Column(name = "product_id")
  private Long productId;

  @Column(name = "article_name", nullable = false)
  private String articleName;

  @Column(name = "article_description", nullable = false)
  private String articleDescription;

  @Column(name = "article_seo", nullable = false)
  private String articleSeo;
}
