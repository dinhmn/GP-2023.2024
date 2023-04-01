package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
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
@IdClass(OrderEntityKey.class)
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

  @Id
  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "product_id", nullable = false)
  private Long cartId;

  @Column(name = "user_information_id")
  private Long userInformationId;

  @Column(name = "order_code", nullable = false)
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String orderCode;

  @Column(name = "order_total_price", nullable = false)
  private BigDecimal orderTotalPrice;

  @Column(name = "order_status")
  private Boolean orderStatus = Boolean.FALSE;
}
