package com.graduationproject.backend.backendwebsiteshoe.helper;

import com.graduationproject.backend.backendwebsiteshoe.common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.entity.SubscribeEntity;
import com.graduationproject.backend.backendwebsiteshoe.forms.SubscribeForm;
import com.graduationproject.backend.backendwebsiteshoe.forms.SubscribeFormPage;
import com.graduationproject.backend.backendwebsiteshoe.service.SubscribeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Implement helper of subscribe.
 *
 * @author Mai Ngoc Dinh
 */
@Component
public class SubscribeHelper {

  @Autowired
  CommonService commonService;

  @Autowired
  SubscribeService subscribeService;

  /**
   * Select all subscribe.
   *
   * @param pageNo        pageNo
   * @param pageSize      pageSize
   * @param sortBy        sortBy
   * @param sortDirection sortDirection
   * @return list article.
   */
  public SubscribeFormPage getAll(int pageNo, int pageSize, String sortBy,
                                  String sortDirection) {
    Pageable pageable = commonService.setPageable(pageSize, pageNo, sortBy, sortDirection);

    // Create pageable instance
    Page<SubscribeEntity> subscribe = subscribeService.getAll(pageable);

    // Get content for page object
    List<SubscribeEntity> subscribeEntityList = subscribe.getContent();

    return SubscribeFormPage.builder()
        .subscribeEntityList(subscribeEntityList)
        .pageNo(subscribe.getNumber())
        .pageSize(subscribe.getSize())
        .totalElements(subscribe.getTotalElements())
        .totalPages(subscribe.getTotalPages())
        .last(subscribe.isLast())
        .build();
  }

  /**
   * Select all subscribe.
   *
   * @param pageNo        pageNo
   * @param pageSize      pageSize
   * @param sortBy        sortBy
   * @param sortDirection sortDirection
   * @param searchValue   searchValue
   * @return list article.
   */
  public SubscribeFormPage getAllByValue(int pageNo, int pageSize, String sortBy,
                                         String sortDirection, String searchValue) {
    Pageable pageable = commonService.setPageable(pageSize, pageNo, sortBy, sortDirection);

    // Create pageable instance
    Page<SubscribeEntity> subscribe = subscribeService.getAll(searchValue, pageable);

    // Get content for page object
    List<SubscribeEntity> subscribeEntityList = subscribe.getContent();

    return SubscribeFormPage.builder()
        .subscribeEntityList(subscribeEntityList)
        .pageNo(subscribe.getNumber())
        .pageSize(subscribe.getSize())
        .totalElements(subscribe.getTotalElements())
        .totalPages(subscribe.getTotalPages())
        .last(subscribe.isLast())
        .build();
  }

  /**
   * Insert new entity of article.
   *
   * @param subscribeForm subscribeForm
   * @return entity category.
   */
  public SubscribeEntity insert(@NonNull SubscribeForm subscribeForm) {
    SubscribeEntity articleEntity =
        this.toBuildSubscribe(subscribeForm);

    return subscribeService.insert(articleEntity);
  }

  /**
   * To build entity.
   *
   * @param subscribeForm subscribeForm
   * @return entity category.
   */
  private SubscribeEntity toBuildSubscribe(SubscribeForm subscribeForm) {
    SubscribeEntity entity = new SubscribeEntity();
    entity.setSubscribeEmail(subscribeForm.getSubscribeEmail());
    commonService.setCommonCreatedEntity(entity);

    return entity;
  }
}
