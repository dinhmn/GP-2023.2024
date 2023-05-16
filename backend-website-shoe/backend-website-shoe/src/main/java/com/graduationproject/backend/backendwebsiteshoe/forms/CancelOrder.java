package com.graduationproject.backend.backendwebsiteshoe.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CancelOrder {

  private Long userId;

  private String userNote;
}
