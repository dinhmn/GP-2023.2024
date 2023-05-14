package com.graduationproject.backend.backendwebsiteshoe.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create template jasper report.
 *
 * @author Mai Ngoc Dinh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderJasperModel {

  private Customer customer;

  private List<Order> orderList;

  private Long orderId;

  private String orderCode;

  private String totalPriceOfAllProduct;

  private String totalQuantityOfAllProduct;

  private String orderStatus;

  /**
   * Class customer.
   */
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Customer {

    private Long userId;

    private String customerOrderCode;

    private String customerFirstName;

    private String customerLastName;

    private String customerAddress;

    private String customerPhone;

    private String customerEmail;

    private String createdDate;

    private String customerNote;
  }

  /**
   * Class order.
   */
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Order {
    private Long productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private BigDecimal productTotalPrice;

    private String productSize;

    private Date createdDateOrder;

    private String status;
  }
}
