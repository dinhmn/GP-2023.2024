package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graduationproject.backend.backendwebsiteshoe.common.Constant;
import com.graduationproject.backend.backendwebsiteshoe.dto.IArticleDTO;
import com.graduationproject.backend.backendwebsiteshoe.entity.ArticleEntity;
import com.graduationproject.backend.backendwebsiteshoe.forms.ArticleForm;
import com.graduationproject.backend.backendwebsiteshoe.forms.ArticleFormPage;
import com.graduationproject.backend.backendwebsiteshoe.helper.ArticleHelper;
import com.graduationproject.backend.backendwebsiteshoe.model.ArticleModel;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Implement controller of article.
 *
 * @author Mai Ngoc Dinh.
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/article")
public class ArticleController {

  @Autowired
  ArticleHelper articleHelper;

  /**
   * Get all category.
   *
   * @param pageNo        pageNo
   * @param pageSize      pageSize
   * @param sortBy        sortBy
   * @param sortDirection sortDirection
   * @return list object.
   */
  @GetMapping(value = "/init")
  public ArticleFormPage selectAll(
      @RequestParam(value = "page_no", defaultValue = Constant.DEFAULT_PAGE_NUMBER) int pageNo,
      @RequestParam(value = "page_size", defaultValue = Constant.DEFAULT_PAGE_SIZE) int pageSize,
      @RequestParam(value = "sort_direction", defaultValue = Constant.DEFAULT_SORT_DIRECTION)
          String sortDirection,
      @RequestParam(value = "sort_by", defaultValue = "article_id") String sortBy) {
    return articleHelper.getAllArticle(pageNo, pageSize, sortBy, sortDirection);
  }

  /**
   * Get all category.
   *
   * @param pageNo        pageNo
   * @param pageSize      pageSize
   * @param sortBy        sortBy
   * @param sortDirection sortDirection
   * @return list object.
   */
  @GetMapping(value = "/init/pageable")
  public ArticleFormPage selectAllArticle(
      @RequestParam(value = "page_no", defaultValue = Constant.DEFAULT_PAGE_NUMBER) int pageNo,
      @RequestParam(value = "page_size", defaultValue = Constant.DEFAULT_PAGE_SIZE) int pageSize,
      @RequestParam(value = "sort_direction", defaultValue = Constant.DEFAULT_SORT_DIRECTION)
          String sortDirection,
      @RequestParam(value = "sort_by", defaultValue = "article_id") String sortBy) {
    return articleHelper.getAllArticleUser(pageNo, pageSize, sortBy, sortDirection);
  }

  /**
   * Get all category.
   *
   * @param articleId articleId
   * @return list object.
   */
  @GetMapping(value = "/init/{articleId}")
  public IArticleDTO selectByArticleId(@PathVariable Long articleId) {
    return articleHelper.getArticleByKey(articleId).get();
  }

  /**
   * Register new product.
   *
   * @param articleForm articleForm
   * @return response entity
   */
  @PostMapping(value = "/register")
  public ResponseEntity<ArticleEntity> registerProduct(@RequestPart("article") String article,
                                                       @RequestPart("file") MultipartFile file)
      throws DataAccessException, JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    ArticleEntity articleEntity = null;
    ArticleForm articleForm = new ArticleForm();
    articleForm.setFile(file);
    articleForm.setArticleModel(mapper.readValue(article, ArticleModel.class));
    try {
      articleEntity = articleHelper.insert(articleForm);
    } catch (DataAccessException | IOException dataAccessException) {
      dataAccessException.printStackTrace();
    }

    return new ResponseEntity<>(articleEntity, HttpStatus.OK);
  }

  /**
   * Update new article.
   *
   * @param articleForm articleForm
   * @return response entity
   */
  @PutMapping(value = "/update")
  public ResponseEntity<ArticleEntity> update(@RequestBody ArticleForm articleForm)
      throws DataAccessException, IOException {
    ArticleEntity articleEntity = null;
    try {
      articleEntity = articleHelper.update(articleForm);
    } catch (DataAccessException | IOException dataAccessException) {
      dataAccessException.printStackTrace();
    }

    return new ResponseEntity<>(articleEntity, HttpStatus.OK);
  }

  /**
   * Delete article.
   *
   * @param articleId articleId
   * @return response 200 or response 500
   */
  @DeleteMapping(value = "/delete/{articleId}")
  public ResponseEntity<Map<String, Boolean>> updateCategory(@PathVariable String articleId) {
    Boolean action =
        articleHelper.delete(Long.parseLong(articleId));
    Map<String, Boolean> response = new HashMap<>();
    response.put("Deleted", action);

    return ResponseEntity.ok(response);
  }
}
