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
 * Rate entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tbl_rates")
public class RatesEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public RatesEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rate_id", nullable = false)
  private Long rateId;

  @Column(name = "product_id")
  private Long productId;

  @Column(name = "article_id")
  private Long articleId;

  @Column(name = "comment_id")
  private Long commentId;

  @Column(name = "star_rating", length = 10)
  private Double starRating;
}
