package com.graduationproject.backend.backendwebsiteshoe.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

/**
 * Order entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tbl_order")
public class OrderEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public OrderEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "orderId", nullable = false)
  private Long orderId;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "cart_id", nullable = false)
  private Long cartId;

  @Column(name = "user_information_id")
  private Long userInformationId;

  @Column(name = "order_code", nullable = false)
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String orderCode;

  @Column(name = "order_total_price", nullable = false)
  private BigDecimal orderTotalPrice;

  @Column(name = "order_status", length = 1)
  private String orderStatus;
}
