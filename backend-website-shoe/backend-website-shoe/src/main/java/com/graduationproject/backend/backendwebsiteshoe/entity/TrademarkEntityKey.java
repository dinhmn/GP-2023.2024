package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;

/**
 * Trademark entity key.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
public class TrademarkEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "trademark_id", nullable = false)
  private Long trademarkId;

  /**
   * Set PK.
   *
   * @param trademarkId trademarkId
   * @return key.
   */
  public TrademarkEntityKey setPk(Long trademarkId) {
    setTrademarkId(trademarkId);
    return this;
  }
}
