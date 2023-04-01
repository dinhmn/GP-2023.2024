package com.graduationproject.backend.backendwebsiteshoe.common;

import com.graduationproject.backend.backendwebsiteshoe.entity.BaseEntity;
import java.util.Date;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Common service.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class CommonService {

  /**
   * Set common column when create entity.
   *
   * @param entity Entity
   * @param <T>    Entity
   */
  public <T extends BaseEntity> void setCommonCreatedEntity(T entity) {
    entity.setCreatedBy(1);
    entity.setCreatedDate(new Date());
    entity.setUpdatedBy(1);
    entity.setUpdatedDate(new Date());
    entity.setStatus(Boolean.TRUE);
  }

  /**
   * Set common column when create entity.
   *
   * @param entity Entity
   * @param <T>    Entity
   */
  public <T extends BaseEntity> void setCommonUpdateEntity(T entity) {
    entity.setCreatedBy(entity.getCreatedBy());
    entity.setCreatedDate(entity.getCreatedDate());
    entity.setUpdatedBy(1);
    entity.setUpdatedDate(new Date());
    entity.setStatus(Boolean.TRUE);
  }

  /**
   * Set name.
   *
   * @param name name
   * @return seo name
   */
  public String setSeo(String name) {
    return name.replace(" ", "-");
  }

  /**
   * Set status of item.
   *
   * @param status status
   * @return TRUE or FALSE
   */
  public Boolean setStatusOfItem(String status) {
    if (Constant.TRUE.equals(status)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  /**
   * Set information pageable.
   *
   * @param pageSize      pageSize
   * @param pageNo        pageNo
   * @param sortBy        sortBy
   * @param sortDirection sortDirection
   * @return pageable
   */
  public Pageable setPageable(int pageSize, int pageNo, String sortBy, String sortDirection) {
    Sort sort =
        sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();

    // Create pageable instance
    return PageRequest.of(pageNo, pageSize, sort);
  }
}
