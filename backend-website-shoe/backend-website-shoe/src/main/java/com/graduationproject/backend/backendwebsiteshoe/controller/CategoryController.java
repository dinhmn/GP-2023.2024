package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.graduationproject.backend.backendwebsiteshoe.common.Constant;
import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntity;
import com.graduationproject.backend.backendwebsiteshoe.forms.CategoryForm;
import com.graduationproject.backend.backendwebsiteshoe.helper.CategoryHelper;
import com.graduationproject.backend.backendwebsiteshoe.model.CategoryModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implement controller of category.
 *
 * @author Mai Ngoc Dinh.
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/categories")
public class CategoryController {

  @Autowired
  CategoryHelper categoryHelper;

  /**
   * Get all category.
   *
   * @return list object.
   */
  @GetMapping(value = "/init")
  public List<CategoryModel> selectAllCategory() {
    return categoryHelper.getAllCategory();
  }

  /**
   * Get all category.
   *
   * @param pageNo        page_no
   * @param pageSize      page_size
   * @param sortDirection sort_direction
   * @param sortBy        sort_by
   * @return list object.
   */
  @GetMapping(value = "/init/pageable")
  public CategoryForm selectAllCategoryPaginated(
      @RequestParam(value = "page_no", defaultValue = Constant.DEFAULT_PAGE_NUMBER) int pageNo,
      @RequestParam(value = "page_size", defaultValue = Constant.DEFAULT_PAGE_SIZE) int pageSize,
      @RequestParam(value = "sort_direction", defaultValue = Constant.DEFAULT_SORT_DIRECTION)
          String sortDirection,
      @RequestParam(value = "sort_by", defaultValue = "category_id") String sortBy,
      @RequestParam(value = "search_value", defaultValue = Constant.EMPTY_SPACE)
          String searchValue) {
    return categoryHelper
        .getAllCategoryPageable(pageNo, pageSize, sortBy, sortDirection, searchValue);
  }

  /**
   * Get all category.
   *
   * @param categoryId  categoryId
   * @param trademarkId trademarkId
   * @return list object.
   */
  @GetMapping(value = "/init/{trademarkId}/{categoryId}")
  public CategoryModel selectById(@PathVariable String categoryId,
                                  @PathVariable String trademarkId) {
    return categoryHelper.getById(Long.parseLong(categoryId), Long.parseLong(trademarkId));
  }

  /**
   * Register new category.
   *
   * @param categoryModel categoryModel
   * @return response entity
   */
  @PostMapping(value = "/register")
  public ResponseEntity<CategoryEntity> registerCategory(@RequestBody CategoryModel categoryModel) {
    CategoryEntity categoryEntity = categoryHelper.insertOrUpdate(categoryModel, "insert");
    return new ResponseEntity<>(categoryEntity, HttpStatus.OK);
  }

  /**
   * Update category.
   *
   * @param categoryModel categoryModel
   * @param categoryId    categoryId
   * @param trademarkId   trademarkId
   * @return response entity
   */
  @PutMapping(value = "/update/{trademarkId}/{categoryId}")
  public ResponseEntity<CategoryEntity> updateCategory(@RequestBody CategoryModel categoryModel,
                                                       @PathVariable final String categoryId,
                                                       @PathVariable final String trademarkId) {
    categoryModel.setCategoryId(Long.parseLong(categoryId));
    categoryModel.setTrademarkId(Long.parseLong(trademarkId));
    CategoryEntity categoryEntity = categoryHelper.insertOrUpdate(categoryModel, "update");

    return new ResponseEntity<>(categoryEntity, HttpStatus.OK);
  }

  /**
   * Delete category.
   *
   * @param categoryId  categoryId
   * @param trademarkId trademarkId
   * @return response entity 200.
   */
  @DeleteMapping(value = "/delete/{trademarkId}/{categoryId}")
  public ResponseEntity<Map<String, Boolean>> updateCategory(@PathVariable String categoryId,
                                                             @PathVariable String trademarkId) {

    Boolean action =
        categoryHelper.deleteByPrimaryKey(Long.parseLong(categoryId), Long.parseLong(trademarkId));
    Map<String, Boolean> response = new HashMap<>();
    response.put("Deleted", action);
    return ResponseEntity.ok(response);
  }
}
