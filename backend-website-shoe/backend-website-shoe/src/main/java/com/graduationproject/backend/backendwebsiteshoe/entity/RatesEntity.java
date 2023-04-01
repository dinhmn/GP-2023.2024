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
 * Rate entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(RatesEntityKey.class)
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
