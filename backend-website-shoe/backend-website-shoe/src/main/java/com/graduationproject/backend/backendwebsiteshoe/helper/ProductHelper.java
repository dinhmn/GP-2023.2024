package com.graduationproject.backend.backendwebsiteshoe.helper;

import com.graduationproject.backend.backendwebsiteshoe.Common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.Common.Constant;
import com.graduationproject.backend.backendwebsiteshoe.dto.IOneProduct;
import com.graduationproject.backend.backendwebsiteshoe.dto.IProduct;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductSizeEntity;
import com.graduationproject.backend.backendwebsiteshoe.model.ProductColorModel;
import com.graduationproject.backend.backendwebsiteshoe.model.ProductModel;
import com.graduationproject.backend.backendwebsiteshoe.model.ProductSizeModel;
import com.graduationproject.backend.backendwebsiteshoe.model.SourceImageModel;
import com.graduationproject.backend.backendwebsiteshoe.service.ProductService;
import com.graduationproject.backend.backendwebsiteshoe.service.ProductSizeService;
import com.graduationproject.backend.backendwebsiteshoe.service.SourceImageService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
  SourceImageService sourceImageService;

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
   * Select all category.
   *
   * @return list category.
   */
  public ProductModel getById(Long categoryId, Long productId) {
    List<IOneProduct> productList = productService.getProductByKey(categoryId, productId);

    return this.toBuildProductModel(productList);
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
    productSizeService.insertAll(productSizeModelList);

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
      List<ProductSizeEntity> productSizeEntityList =
          productModel.getProductSizeModelList().stream()
              .map(model -> productSizeService.update(model))
              .toList();
      System.out.println(productSizeEntityList);
      // Save file image of product
      sourceImageService
          .insertOrUpdateImages(productEntity.getProductId(), files, productModel, Constant.UPDATE);
      return productEntity;
    }

    // After product entity not exists => create entity to insert.
    ProductEntity productEntity = productService.insert(productModel);

    // insert product size
    List<ProductSizeModel> productSizeModelList = productModel.getProductSizeModelList();
    productSizeService.insertAll(productSizeModelList);

    // insert product model
    sourceImageService
        .insertOrUpdateImages(productEntity.getProductId(), files, productModel, Constant.INSERT);

    return productEntity;
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
            .productColorId(Long.parseLong(color.getProductColorId()))
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