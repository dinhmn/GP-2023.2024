package com.graduationproject.backend.backendwebsiteshoe.forms;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 * From support order.
 *
 * @author Mai Ngoc Dinh
 */
@Data
@Builder
public class OrderFormSupport {

  private String fullName;

  private Long orderId;

  private String customerAddress;

  private String orderCode;

  private Date createdDate;

  private String orderStatus;

  private String quantity;

  private String totalPrice;
}
