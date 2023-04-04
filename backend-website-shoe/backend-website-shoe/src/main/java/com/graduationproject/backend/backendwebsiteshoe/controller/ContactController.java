package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.graduationproject.backend.backendwebsiteshoe.common.Constant;
import com.graduationproject.backend.backendwebsiteshoe.entity.ContactEntity;
import com.graduationproject.backend.backendwebsiteshoe.forms.ContactFormPage;
import com.graduationproject.backend.backendwebsiteshoe.helper.ContactHelper;
import com.graduationproject.backend.backendwebsiteshoe.model.ContactModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implement controller of contact.
 *
 * @author Mai Ngoc Dinh.
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/contact")
public class ContactController {

  @Autowired
  ContactHelper contactHelper;

  /**
   * Get all contact.
   *
   * @param pageNo        pageNo
   * @param pageSize      pageSize
   * @param sortBy        sortBy
   * @param sortDirection sortDirection
   * @return list object.
   */
  @GetMapping(value = "/init")
  public ContactFormPage selectAll(
      @RequestParam(value = "page_no", defaultValue = Constant.DEFAULT_PAGE_NUMBER) int pageNo,
      @RequestParam(value = "page_size", defaultValue = Constant.DEFAULT_PAGE_SIZE) int pageSize,
      @RequestParam(value = "sort_direction", defaultValue = Constant.DEFAULT_SORT_DIRECTION)
          String sortDirection,
      @RequestParam(value = "sort_by", defaultValue = "contact_id") String sortBy) {
    return contactHelper.getAllContact(pageNo, pageSize, sortBy, sortDirection);
  }

  /**
   * Get all contact.
   *
   * @param pageNo        pageNo
   * @param pageSize      pageSize
   * @param sortBy        sortBy
   * @param sortDirection sortDirection
   * @return list object.
   */
  @GetMapping(value = "/init/pageable")
  public ContactFormPage selectAllArticle(
      @RequestParam(value = "search_value") String searchValue,
      @RequestParam(value = "page_no", defaultValue = Constant.DEFAULT_PAGE_NUMBER) int pageNo,
      @RequestParam(value = "page_size", defaultValue = Constant.DEFAULT_PAGE_SIZE) int pageSize,
      @RequestParam(value = "sort_direction", defaultValue = Constant.DEFAULT_SORT_DIRECTION)
          String sortDirection,
      @RequestParam(value = "sort_by", defaultValue = "contact_id") String sortBy) {
    return contactHelper.getAllContactUser(pageNo, pageSize, sortBy, sortDirection, searchValue);
  }

  /**
   * Register new contact.
   *
   * @param contactModel contactModel
   * @return response entity
   */
  @PostMapping(value = "/register")
  public ResponseEntity<ContactEntity> registerProduct(
      @RequestBody @NonNull ContactModel contactModel) {
    ContactEntity contactEntity = contactHelper.insert(contactModel);
    return new ResponseEntity<>(contactEntity, HttpStatus.OK);
  }

}
