package com.graduationproject.backend.backendwebsiteshoe.forms;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class OrderFormSupport {

  private String fullName;

  private Long orderId;

  private String customerAddress;

  private String orderCode;

  private Date createdDate;

  private String orderStatus;
}
