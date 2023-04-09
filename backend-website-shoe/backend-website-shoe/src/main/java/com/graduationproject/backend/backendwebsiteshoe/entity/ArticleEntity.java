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
