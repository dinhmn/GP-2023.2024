package com.graduationproject.backend.backendwebsiteshoe.forms;

import com.graduationproject.backend.backendwebsiteshoe.dto.IArticleDTO;
import com.graduationproject.backend.backendwebsiteshoe.entity.ArticleEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Form article response pageable.
 *
 * @author Mai Ngoc Dinh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleFormPage {

  private List<ArticleEntity> articleEntityList;

  private List<IArticleDTO> articleDTOList;

  private int pageNo;

  private int pageSize;

  private long totalElements;

  private int totalPages;

  private boolean last;

}
