package com.graduationproject.backend.backendwebsiteshoe.dto;

import java.math.BigDecimal;
import java.util.Date;

public interface IOrder {

  Long getProductId();

  Long getUserId();

  Long getOrderId();

  String getProductName();

  BigDecimal getProductPrice();

  Integer getProductQuantity();

  Long getCartId();

  String getCustomerAddress();

  String getCustomerFirstName();

  String getCustomerLastName();

  String getCustomerEmail();

  String getCustomerPhone();

  Date getCreatedDate();

  String getOrderCode();

  String getCustomerNote();

}
