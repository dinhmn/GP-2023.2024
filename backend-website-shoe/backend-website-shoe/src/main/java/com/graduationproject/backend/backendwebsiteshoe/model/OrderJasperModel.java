package com.graduationproject.backend.backendwebsiteshoe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Create template jasper report.
 *
 * @author Mai Ngoc Dinh
 */
@Data
@AllArgsConstructor
public class OrderJasperModel {

  private Customer customer;

  private List<Order> orderList;

  /**
   * Class customer.
   */
  @Data
  @AllArgsConstructor
  public class Customer {

    private String userId;

    private String customerOrderCode;

    private String customerFirstName;

    private String customerLastName;

    private String customerAddress;

    private String customerPhone;

    private String customerEmail;

    private String createdDate;
  }

  /**
   * Class order.
   */
  @Data
  @AllArgsConstructor
  public class Order {
    private Long productId;

    private String productName;

    private Double productPrice;

    private Integer productQuantity;

    private Double productTotalPrice;
  }
}
