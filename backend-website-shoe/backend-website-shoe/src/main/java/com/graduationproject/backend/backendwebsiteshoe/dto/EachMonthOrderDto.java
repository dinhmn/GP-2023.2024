package com.graduationproject.backend.backendwebsiteshoe.dto;

import java.math.BigDecimal;

public interface EachMonthOrderDto {

  String getMonth();

  BigDecimal getTotalPrice();
}
