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
 * Trademark entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(TrademarkEntityKey.class)
@Table(name = "tbl_trademark")
public class TrademarkEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public TrademarkEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "trademark_id", nullable = false)
  private Long trademarkId;

  @Column(name = "trademark_name", nullable = false)
  private String trademarkName;

  @Column(name = "seo", nullable = false)
  private String seo;

}
