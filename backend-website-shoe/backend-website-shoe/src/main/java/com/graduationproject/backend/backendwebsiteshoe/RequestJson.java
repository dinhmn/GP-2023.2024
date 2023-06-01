package com.graduationproject.backend.backendwebsiteshoe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RequestJson {
  private String designatedDay;
  private String famiExclusionFlag;
  private String buppanExclusionFlag;
  private String ticketExclusionFlag;
  private String receivableFlag;
  private String plans;
  private String menuNo;
  private Integer startIndex;
  private Integer count;
}
