package com.graduationproject.backend.backendwebsiteshoe.entity;

import java.io.Serializable;
import javax.persistence.Column;
import lombok.Data;

/**
 * Order entity key.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
public class OrderEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "order_id", nullable = false)
  private Long orderId;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  /**
   * Set PK.
   *
   * @param orderId orderId
   * @param userId  userId
   * @return key.
   */
  public OrderEntityKey setPk(Long orderId, Long userId) {
    setOrderId(orderId);
    setUserId(userId);
    return this;
  }
}
