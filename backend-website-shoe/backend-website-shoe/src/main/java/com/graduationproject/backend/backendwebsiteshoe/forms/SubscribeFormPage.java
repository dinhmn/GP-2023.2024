package com.graduationproject.backend.backendwebsiteshoe.forms;

import com.graduationproject.backend.backendwebsiteshoe.entity.SubscribeEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Form subscribe response pageable.
 *
 * @author Mai Ngoc Dinh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeFormPage {

  private List<SubscribeEntity> subscribeEntityList;

  private int pageNo;

  private int pageSize;

  private long totalElements;

  private int totalPages;

  private boolean last;

}
