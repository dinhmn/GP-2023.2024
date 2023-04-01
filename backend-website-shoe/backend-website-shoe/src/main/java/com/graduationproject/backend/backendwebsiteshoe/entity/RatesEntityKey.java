package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;

/**
 * Cart entity key.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
public class RatesEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "rate_id", nullable = false)
  private Long rateId;

  /**
   * Set PK.
   *
   * @param rateId rateId
   * @return key.
   */
  public RatesEntityKey setPk(Long rateId) {
    setRateId(rateId);
    return this;
  }
}
