package com.graduationproject.backend.backendwebsiteshoe.forms;

import com.graduationproject.backend.backendwebsiteshoe.dto.IOrder;
import com.graduationproject.backend.backendwebsiteshoe.model.OrderJasperModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Form order response pageable.
 *
 * @author Mai Ngoc Dinh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderForm {

  private List<IOrder> orderList;

  private int pageNo;

  private int pageSize;

  private long totalElements;

  private int totalPages;

  private boolean last;
}
