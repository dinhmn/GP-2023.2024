package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graduationproject.backend.backendwebsiteshoe.common.Constant;
import com.graduationproject.backend.backendwebsiteshoe.dto.IProduct;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductEntity;
import com.graduationproject.backend.backendwebsiteshoe.forms.ProductForm;
import com.graduationproject.backend.backendwebsiteshoe.helper.ProductHelper;
import com.graduationproject.backend.backendwebsiteshoe.model.ProductModel;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
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
 * Implement controller of product.
 *
 * @author Mai Ngoc Dinh.
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/products/")
public class ProductController {

  @Autowired
  ProductHelper productHelper;

  /**
   * Get all product.
   *
   * @return list object.
   */
  @GetMapping(value = "/init")
  public List<IProduct> selectAllProduct() {
    return productHelper.getAllProduct();
  }

  /**
   * Get all product.
   *
   * @param pageNo        pageNo
   * @param pageSize      pageSize
   * @param sortBy        sortBy
   * @param sortDirection sortDirection
   * @return list object.
   */
  @GetMapping(value = "/init/pageable")
  public ProductForm selectAllProduct(
      @RequestParam(value = "page_no", defaultValue = Constant.DEFAULT_PAGE_NUMBER) int pageNo,
      @RequestParam(value = "page_size", defaultValue = Constant.DEFAULT_PAGE_SIZE) int pageSize,
      @RequestParam(value = "sort_direction", defaultValue = Constant.DEFAULT_SORT_DIRECTION)
          String sortDirection,
      @RequestParam(value = "sort_by", defaultValue = "product_id") String sortBy,
      @RequestParam(value = "search_value", defaultValue = Constant.EMPTY_SPACE) String searchValue) {
    return productHelper.getAllProduct(pageNo, pageSize, sortBy, sortDirection, searchValue);
  }

  /**
   * Get all product.
   *
   * @param searchValue searchValue
   * @return list object.
   */
  @GetMapping(value = "/search")
  public List<IProduct> search(@RequestParam(value = "search_value") String searchValue) {
    return productHelper.getAllProduct(searchValue);
  }

  /**
   * Get all product.
   *
   * @param productId  productId
   * @param categoryId categoryId
   * @return list object.
   */
  @GetMapping(value = "/init/{categoryId}/{productId}")
  public ProductModel selectById(@PathVariable String productId,
                                 @PathVariable String categoryId) {
    return productHelper.getById(Long.parseLong(categoryId),Long.parseLong(productId));
  }

  /**
   * Register new product.
   *
   * @param product product
   * @param files   files
   * @return response entity
   */
  @PostMapping(value = "/register")
  public ResponseEntity<ProductEntity> registerProduct(@RequestPart("product") String product,
                                                       @RequestPart("files")
                                                           List<MultipartFile> files)
      throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    ProductModel productModel = mapper.readValue(product, ProductModel.class);
    ProductEntity productEntity = null;
    try {
      productEntity = productHelper.insert(productModel, files);
    } catch (DataAccessException | FileUploadException fileUploadException) {
      fileUploadException.printStackTrace();
    }

    return new ResponseEntity<>(productEntity, HttpStatus.OK);
  }

  /**
   * Update product.
   *
   * @param productModel productModel
   * @param categoryId   categoryId
   * @param productId    productId
   * @param files        files
   * @return response entity
   */
  @PutMapping(value = "/update/{categoryId}/{productId}")
  public ResponseEntity<ProductEntity> updateCategory(@RequestBody ProductModel productModel,
                                                      @PathVariable String categoryId,
                                                      @PathVariable String productId,
                                                      @RequestBody List<MultipartFile> files)
      throws IOException {
    ProductEntity productEntity = null;
    try {
      productModel.setProductId(Long.parseLong(productId));
      productModel.setCategoryId(Long.parseLong(categoryId));
      productEntity = productHelper.update(productModel, files);
    } catch (FileUploadException fileUploadException) {
      fileUploadException.printStackTrace();
    }

    return new ResponseEntity<>(productEntity, HttpStatus.OK);
  }

  /**
   * Delete product.
   *
   * @param categoryId categoryId
   * @param productId  productId
   * @return response 200 or response 500
   */
  @DeleteMapping(value = "/delete/{categoryId}/{productId}")
  public ResponseEntity<Map<String, Boolean>> updateCategory(@PathVariable String categoryId,
                                                             @PathVariable String productId) {
    Boolean action =
        productHelper.delete(Long.parseLong(categoryId), Long.parseLong(productId));
    Map<String, Boolean> response = new HashMap<>();
    response.put("Deleted", action);

    return ResponseEntity.ok(response);
  }

}
