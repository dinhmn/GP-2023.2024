package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
