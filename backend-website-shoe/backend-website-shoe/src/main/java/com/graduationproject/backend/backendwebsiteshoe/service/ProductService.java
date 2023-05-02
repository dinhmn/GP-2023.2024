package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.dto.FilterProduct;
import com.graduationproject.backend.backendwebsiteshoe.dto.IOneProduct;
import com.graduationproject.backend.backendwebsiteshoe.dto.IProduct;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductEntityKey;
import com.graduationproject.backend.backendwebsiteshoe.model.ProductModel;
import com.graduationproject.backend.backendwebsiteshoe.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Implement service of item product.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  CommonService commonService;

  /**
   * Get all product.
   *
   * @return list of entity product.
   */
  public List<IProduct> getAll() {
    return productRepository.findAllProduct();
  }

  /**
   * Get all product.
   *
   * @param pageable    pageable
   * @param searchValue searchValue
   * @return list of entity product.
   */
  public Page<IProduct> getAll(Pageable pageable, String searchValue) {
    return productRepository.findAllProduct(searchValue, pageable);
  }

  /**
   * Get all product.
   *
   * @param pageable    pageable
   * @param categoryId categoryId
   * @return list of entity product.
   */
  public Page<IProduct> getAllByCategoryId(Pageable pageable, Long categoryId) {
    return productRepository.findAllProduct(categoryId, pageable);
  }

  /**
   * Get all product.
   *
   * @param filterProduct filterProduct
   * @param pageable      pageable
   * @return list of entity product.
   */
  public Page<IProduct> getAll(FilterProduct filterProduct, Pageable pageable) {
    return productRepository
        .findAllProduct(filterProduct.getSearchValue(), filterProduct.getCategoryId(),
            filterProduct.getPriceMin(), filterProduct.getPriceMax(), filterProduct.getSizeList(),
            filterProduct.getColorList(), pageable);
  }

  /**
   * Get all product.
   *
   * @param categoryId categoryId
   * @param limitItem limitItem
   * @return list of entity product.
   */
  public List<IProduct> getAll(Long categoryId, Integer limitItem) {
    return productRepository.findAllProduct(categoryId, limitItem);
  }

  /**
   * Get all product.
   *
   * @param limitItem limitItem
   * @return list of entity product.
   */
  public List<IProduct> getAllProductSale(Integer limitItem) {
    return productRepository.findAllProductSale(limitItem);
  }

  /**
   * Get product item.
   *
   * @return entity product.
   */
  public List<IOneProduct> getProductByKey(Long categoryId, Long productId) {
    return productRepository.findProductByKey(productId, categoryId);
  }

  /**
   * Get product item.
   *
   * @return entity product.
   */
  public Optional<ProductEntity> getProductById(Long categoryId, Long productId) {
    ProductEntityKey productEntityKey = new ProductEntityKey();
    productEntityKey.setPk(productId, categoryId);
    return productRepository.findById(productEntityKey);
  }

  /**
   * Insert new entity of product.
   *
   * @param productModel productModel
   * @return entity product.
   */
  public ProductEntity insert(ProductModel productModel) {
    ProductEntity productEntity = this.toBuildProductUseInsert(productModel);
    return productRepository.save(productEntity);
  }

  /**
   * Insert new entity of product.
   *
   * @param productModel productModel
   * @return entity product.
   */
  public ProductEntity update(ProductModel productModel) {
    ProductEntityKey productEntityKey = new ProductEntityKey();
    productEntityKey.setPk(productEntityKey.getProductId(), productEntityKey.getCategoryId());

    Optional<ProductEntity> entity = productRepository.findById(productEntityKey);

    return entity.map(
        productEntity -> productRepository.save(this.toBuildProduct(productEntity, productModel)))
        .orElseGet(() -> productRepository.save(this.toBuildProductUseInsert(productModel)));

  }

  /**
   * Delete category by primary key.
   *
   * @param productId  productId
   * @param categoryId categoryId
   */
  public void delete(@NonNull Long productId, @NonNull Long categoryId) {
    ProductEntityKey productEntityKey = new ProductEntityKey();
    productEntityKey.setPk(productId, categoryId);
    productRepository.deleteById(productEntityKey);
  }

  /**
   * To build product.
   *
   * @param databaseEntity databaseEntity
   * @param productModel   productModel
   * @return entity.
   */
  private ProductEntity toBuildProduct(ProductEntity databaseEntity, ProductModel productModel) {
    databaseEntity.setProductDescription(productModel.getProductDescription());
    databaseEntity.setProductName(productModel.getProductName());
    databaseEntity
        .setProductPrice(BigDecimal.valueOf(Long.parseLong(productModel.getProductPrice())));
    databaseEntity.setProductPriceSale(
        BigDecimal.valueOf(Long.parseLong(productModel.getProductPriceSale())));
    databaseEntity.setProductSeo(commonService.setSeo(productModel.getProductName()));
    databaseEntity.setQuantity(productModel.getQuantity());
    databaseEntity.setUpdatedBy(1);
    databaseEntity.setUpdatedDate(new Date());
    databaseEntity.setStatus(commonService.setStatusOfItem(productModel.getStatus()));

    return databaseEntity;
  }

  /**
   * To build product.
   *
   * @param productModel productModel
   * @return entity
   */
  private ProductEntity toBuildProductUseInsert(ProductModel productModel) {
    ProductEntity productEntity = new ProductEntity();
    productEntity.setProductId(productModel.getProductId());
    productEntity.setCategoryId(productModel.getCategoryId());
    productEntity.setProductDescription(productModel.getProductDescription());
    productEntity.setProductName(productModel.getProductName());
    productEntity
        .setProductPrice(BigDecimal.valueOf(Long.parseLong(productModel.getProductPrice())));
    productEntity.setProductPriceSale(
        BigDecimal.valueOf(Long.parseLong(productModel.getProductPriceSale())));
    productEntity.setProductSeo(commonService.setSeo(productModel.getProductName()));
    productEntity.setQuantity(productModel.getQuantity());
    commonService.setCommonCreatedEntity(productEntity);

    return productEntity;
  }

}
