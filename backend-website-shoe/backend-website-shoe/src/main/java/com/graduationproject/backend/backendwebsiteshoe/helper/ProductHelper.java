package com.graduationproject.backend.backendwebsiteshoe.helper;

import com.graduationproject.backend.backendwebsiteshoe.common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.common.Constant;
import com.graduationproject.backend.backendwebsiteshoe.common.Image;
import com.graduationproject.backend.backendwebsiteshoe.dto.FilterProduct;
import com.graduationproject.backend.backendwebsiteshoe.dto.IComment;
import com.graduationproject.backend.backendwebsiteshoe.dto.IOneProduct;
import com.graduationproject.backend.backendwebsiteshoe.dto.IProduct;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductColorEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductSizeEntity;
import com.graduationproject.backend.backendwebsiteshoe.forms.ProductForm;
import com.graduationproject.backend.backendwebsiteshoe.model.MapProductModel;
import com.graduationproject.backend.backendwebsiteshoe.model.ProductColorModel;
import com.graduationproject.backend.backendwebsiteshoe.model.ProductModel;
import com.graduationproject.backend.backendwebsiteshoe.model.ProductSizeModel;
import com.graduationproject.backend.backendwebsiteshoe.model.SourceImageModel;
import com.graduationproject.backend.backendwebsiteshoe.service.CommentService;
import com.graduationproject.backend.backendwebsiteshoe.service.ProductColorService;
import com.graduationproject.backend.backendwebsiteshoe.service.ProductService;
import com.graduationproject.backend.backendwebsiteshoe.service.ProductSizeService;
import com.graduationproject.backend.backendwebsiteshoe.service.SourceImageService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Implement helper of product.
 *
 * @author Mai Ngoc Dinh
 */
@Component
public class ProductHelper {

  @Autowired
  CommonService commonService;

  @Autowired
  ProductService productService;

  @Autowired
  ProductSizeService productSizeService;

  @Autowired
  ProductColorService productColorService;

  @Autowired
  SourceImageService sourceImageService;

  @Autowired
  CommentService commentService;

  /**
   * Select all product.
   *
   * @return list product.
   */
  @NonNull
  public List<IProduct> getAllProduct() {
    return productService.getAll();
  }

  /**
   * Select all product.
   *
   * @param categoryId categoryId
   * @param limitItem limitItem
   * @return list product.
   */
  @NonNull
  public List<IProduct> getAllProductByCategoryId(Long categoryId, Integer limitItem) {
    return productService.getAll(categoryId, limitItem);
  }

  /**
   * Select all product.
   *
   * @param limitItem limitItem
   * @return list product.
   */
  @NonNull
  public List<IProduct> getAllProductSaleByCategoryId(Integer limitItem) {
    return productService.getAllProductSale(limitItem);
  }

  /**
   * Select all product.
   *
   * @param filterProduct filterProduct
   * @return list product.
   */
  @NonNull
  public ProductForm getAllProduct(FilterProduct filterProduct) {
    Pageable pageable = commonService.setPageable(filterProduct.getPageSize(),
        filterProduct.getPageNo(), filterProduct.getSortBy(), filterProduct.getSortDirection());

    // Create pageable instance
    Page<IProduct> productList = productService.getAll(filterProduct, pageable);

    // Get content for page object
    List<IProduct> listOfProductModel = productList.getContent();

    return ProductForm.builder()
        .productModelList(listOfProductModel)
        .pageNo(productList.getNumber())
        .pageSize(productList.getSize())
        .totalElements(productList.getTotalElements())
        .totalPages(productList.getTotalPages())
        .last(productList.isLast())
        .build();
  }

  /**
   * Select all product.
   *
   * @param pageNo        pageNo
   * @param pageSize      pageSize
   * @param sortBy        sortBy
   * @param sortDirection sortDirection
   * @param searchValue   searchValue
   * @return list product.
   */
  public ProductForm getAllProduct(int pageNo, int pageSize, String sortBy, String sortDirection,
                                   String searchValue) {
    Pageable pageable = commonService.setPageable(pageSize, pageNo, sortBy, sortDirection);

    // Create pageable instance
    Page<IProduct> productList = productService.getAll(pageable, searchValue);

    // Get content for page object
    List<IProduct> listOfProductModel = productList.getContent();

    return ProductForm.builder()
        .productModelList(listOfProductModel)
        .pageNo(productList.getNumber())
        .pageSize(productList.getSize())
        .totalElements(productList.getTotalElements())
        .totalPages(productList.getTotalPages())
        .last(productList.isLast())
        .build();
  }

  /**
   * Select all category.
   *
   * @return list category.
   */
  public ProductModel getById(Long categoryId, Long productId) {
    List<IOneProduct> productList = productService.getProductByKey(categoryId, productId);

    return this.toBuildProductModel(productList);
  }

  /**
   * Select all category.
   *
   * @return list category.
   */
  public MapProductModel getInformationProductByKey(Long categoryId, Long productId) {
    MapProductModel mapProductModel = new MapProductModel();
    ProductModel productModel =
        this.toBuildProductModel(productService.getProductByKey(categoryId, productId));
    List<IComment> commentList = commentService.getAll(productId);
    mapProductModel.setProductModel(productModel);
    mapProductModel.setCommentList(commentList);

    return mapProductModel;
  }

  /**
   * Insert data.
   *
   * @return list category.
   */
  @NonNull
  public ProductEntity insert(ProductModel productModel, List<MultipartFile> files)
      throws IOException {
    // insert data of table product into database
    ProductEntity productEntity = productService.insert(productModel);

    // insert data of table product size into database
    List<ProductSizeModel> productSizeModelList = productModel.getProductSizeModelList();
    productSizeService.insertAll(productSizeModelList, productEntity.getProductId());

    List<ProductColorModel> productColorModelList = productModel.getProductColorModelList();
    productColorService.insertAll(productColorModelList, productEntity.getProductId());

    // Insert image into database
    sourceImageService.insertOrUpdateImages(productEntity.getProductId(), files, new ProductModel(),
        Constant.INSERT);

    return productEntity;
  }

  /**
   * Update product by primary key.
   *
   * @param productModel productModel
   * @return entity product.
   */
  public ProductEntity update(ProductModel productModel, List<MultipartFile> files)
      throws IOException {
    // Get product entity from database.
    Optional<ProductEntity> productInDatabase = productService.getProductById(
        productModel.getCategoryId(), productModel.getProductId());

    // Check product entity have exists.
    if (productInDatabase.isPresent()) {
      // Save product
      ProductEntity productEntity = productService.update(productModel);

      // Update product size
      productSizeService
          .updateAll(productModel.getProductSizeModelList(), productEntity.getProductId());

      // Update product color
      productColorService
          .updateAll(productModel.getProductColorModelList(), productEntity.getProductId());

      // Save file image of product
      sourceImageService
          .insertOrUpdateImages(productEntity.getProductId(), files, productModel, Constant.UPDATE);
      return productEntity;
    }

    // After product entity not exists => create entity to insert.
    ProductEntity productEntity = productService.insert(productModel);

    // insert product size
    List<ProductSizeModel> productSizeModelList = productModel.getProductSizeModelList();
    productSizeService.insertAll(productSizeModelList, productEntity.getProductId());

    // insert product color
    List<ProductColorModel> productColorModelList = productModel.getProductColorModelList();
    productColorService.insertAll(productColorModelList, productEntity.getProductId());

    // insert product model
    sourceImageService
        .insertOrUpdateImages(productEntity.getProductId(), files, productModel, Constant.INSERT);

    return productEntity;
  }

  /**
   * Delete all product.
   *
   * @param categoryId categoryId
   * @param productId  productId
   * @return TRUE or FALSE
   */
  public Boolean delete(Long categoryId, Long productId) {
    // Delete all product color
    List<Long> productColorIdList = productColorService.selectByProductId(productId).stream()
        .map(ProductColorEntity::getProductColorId)
        .toList();
    productColorIdList
        .forEach(productColorId -> productColorService.deleteByPrimaryKey(productColorId));

    // Delete all product size
    List<Long> productSizeIdList = productSizeService.selectByPrimaryKey(productId).stream()
        .map(ProductSizeEntity::getProductSizeId)
        .toList();
    productSizeIdList
        .forEach(productSizeId -> productSizeService.deleteByPrimaryKey(productSizeId));

    // Delete source image
    List<String> productImagesCode =
        List.of(Image.IMAGE_MAIN_PRODUCT.getCode(), Image.IMAGE_SECONDARY_PRODUCT.getCode(),
            Image.IMAGE_COLOR.getCode());
    sourceImageService.deleteByProductId(productId, productImagesCode);

    // Delete product
    Optional<ProductEntity> productEntity = productService.getProductById(categoryId, productId);
    if (productEntity.isPresent()) {
      productService.delete(productId, categoryId);
      return Boolean.TRUE;
    }

    return Boolean.FALSE;
  }

  /**
   * Convert product entity to product model.
   *
   * @param productList productList
   * @return product model.
   */
  private ProductModel toBuildProductModel(List<IOneProduct> productList) {
    if (productList.size() > 0) {
      // Convert image entity to model.
      List<SourceImageModel> sourceImageModelList = new ArrayList<>();
      for (IOneProduct image : productList) {
        SourceImageModel model = SourceImageModel.builder()
            .imageId(image.getImageId())
            .fileSize(image.getFileSize())
            .fileCode(image.getFileCode())
            .fileName(image.getFileName())
            .fileType(image.getFileType())
            .build();
        sourceImageModelList.add(model);
      }

      // Convert product color entity to model.
      List<ProductColorModel> productColorModelList = new ArrayList<>();
      for (IOneProduct color : productList) {
        ProductColorModel model = ProductColorModel.builder()
            .productColorId(Objects.nonNull(color.getProductColorId())
                ? Long.parseLong(color.getProductColorId()) : Constant.ZERO)
            .productColorName(color.getProductColorName())
            .productColorQuantity(Integer.parseInt(color.getProductColorQuantity()))
            .build();
        productColorModelList.add(model);
      }

      // Convert product size entity to model.
      List<ProductSizeModel> productSizeModelList = new ArrayList<>();
      for (IOneProduct size : productList) {
        ProductSizeModel model = ProductSizeModel.builder()
            .productSizeId(Long.parseLong(size.getProductColorId()))
            .productSizeName(size.getProductColorName())
            .productSizeQuantity(Integer.parseInt(size.getProductColorQuantity()))
            .build();
        productSizeModelList.add(model);
      }

      // Convert product entity to model
      return ProductModel.builder()
          .categoryId(Long.parseLong(productList.get(0).getCategoryId()))
          .categoryName(productList.get(0).getCategoryName())
          .productId(Long.parseLong(productList.get(0).getProductId()))
          .productName(productList.get(0).getProductName())
          .productDescription(productList.get(0).getProductDescription())
          .productPrice(productList.get(0).getProductPrice())
          .productPriceSale(productList.get(0).getProductPriceSale())
          .quantity(Integer.parseInt(productList.get(0).getProductQuantity()))
          .status(productList.get(0).getProductStatus())
          .sourceImageModelList(sourceImageModelList)
          .productSizeModelList(productSizeModelList)
          .productColorModelList(productColorModelList)
          .build();
    }

    return null;
  }
}