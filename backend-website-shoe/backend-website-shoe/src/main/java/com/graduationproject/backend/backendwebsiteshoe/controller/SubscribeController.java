package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.graduationproject.backend.backendwebsiteshoe.common.Constant;
import com.graduationproject.backend.backendwebsiteshoe.entity.SubscribeEntity;
import com.graduationproject.backend.backendwebsiteshoe.forms.SubscribeForm;
import com.graduationproject.backend.backendwebsiteshoe.forms.SubscribeFormPage;
import com.graduationproject.backend.backendwebsiteshoe.helper.SubscribeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implement controller of subscribe.
 *
 * @author Mai Ngoc Dinh.
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/subscribe")
public class SubscribeController {

  @Autowired
  SubscribeHelper subscribeHelper;

  /**
   * Get all subscribe.
   *
   * @param pageNo        pageNo
   * @param pageSize      pageSize
   * @param sortBy        sortBy
   * @param sortDirection sortDirection
   * @return list object.
   */
  @GetMapping(value = "/init")
  public SubscribeFormPage selectAll(
      @RequestParam(value = "page_no", defaultValue = Constant.DEFAULT_PAGE_NUMBER) int pageNo,
      @RequestParam(value = "page_size", defaultValue = Constant.DEFAULT_PAGE_SIZE) int pageSize,
      @RequestParam(value = "sort_direction", defaultValue = Constant.DEFAULT_SORT_DIRECTION)
          String sortDirection,
      @RequestParam(value = "sort_by", defaultValue = "subscribe_id") String sortBy) {
    return subscribeHelper.getAll(pageNo, pageSize, sortBy, sortDirection);
  }

  /**
   * Register new subscribe.
   *
   * @param subscribeForm subscribeForm
   * @return response entity
   */
  @PostMapping(value = "/register")
  public ResponseEntity<SubscribeEntity> registerProduct(@RequestBody SubscribeForm subscribeForm) {
    SubscribeEntity subscribeEntity = subscribeHelper.insert(subscribeForm);

    return new ResponseEntity<>(subscribeEntity, HttpStatus.OK);
  }

}
