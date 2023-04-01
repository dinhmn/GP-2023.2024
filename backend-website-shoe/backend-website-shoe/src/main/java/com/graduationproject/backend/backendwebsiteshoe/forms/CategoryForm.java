package com.graduationproject.backend.backendwebsiteshoe.forms;

import com.graduationproject.backend.backendwebsiteshoe.model.CategoryModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Form response pageable.
 *
 * @author Mai Ngoc Dinh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryForm {

  private List<CategoryModel> categoryModelList;

  private int pageNo;

  private int pageSize;

  private long totalElements;

  private int totalPages;

  private boolean last;
}
