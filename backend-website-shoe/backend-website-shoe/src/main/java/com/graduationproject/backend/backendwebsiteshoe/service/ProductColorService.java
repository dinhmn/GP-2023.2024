package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductColorEntity;
import com.graduationproject.backend.backendwebsiteshoe.model.ProductColorModel;
import com.graduationproject.backend.backendwebsiteshoe.repository.ProductColorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implement service of item product color.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class ProductColorService {

  @Autowired
  CommonService commonService;

  @Autowired
  ProductColorRepository productColorRepository;

  /**
   * Insert product color image use create new.
   *
   * @param productColorModel productColorModel
   * @return entity
   */
  public ProductColorEntity insert(ProductColorModel productColorModel, Long productId) {
    ProductColorEntity productColorEntity = this.toBuildProductColor(productColorModel, productId);
    return productColorRepository.save(productColorEntity);
  }

  /**
   * Insert all product color image use create new.
   *
   * @param productColorModelList productColorModelList
   */
  public void insertAll(List<ProductColorModel> productColorModelList, Long productId) {
    List<ProductColorEntity> productSizeEntityList = productColorModelList.stream()
        .map(color -> this.toBuildProductColor(color, productId))
        .toList();
    productColorRepository.saveAll(productSizeEntityList);
  }

  /**
   * Insert product size image use create new.
   *
   * @param productColorModel productSizeModel
   * @param productId         productId
   * @return entity
   */
  public ProductColorEntity update(ProductColorModel productColorModel, Long productId) {
    Optional<ProductColorEntity> productColor =
        productColorRepository.findById(productColorModel.getProductColorId());

    if (productColor.isPresent()) {
      ProductColorEntity productColorEntity =
          this.toBuildProductColor(productColor.get(), productColorModel, productId);
      return productColorRepository.save(productColorEntity);
    }

    ProductColorEntity productColorEntity = this.toBuildProductColor(productColorModel, productId);
    return productColorRepository.save(productColorEntity);
  }

  /**
   * Insert product size image use create new.
   *
   * @param productColorModelList productSizeModel
   * @param productId             productId
   */
  public void updateAll(List<ProductColorModel> productColorModelList, Long productId) {
    List<ProductColorEntity> productColorEntityList = new ArrayList<>();
    for (ProductColorModel productColorModel : productColorModelList) {
      Optional<ProductColorEntity> productColor =
          productColorRepository.findById(productColorModel.getProductColorId());

      ProductColorEntity productColorEntity;
      productColorEntity = productColor
          .map(colorEntity -> this.toBuildProductColor(colorEntity, productColorModel, productId))
          .orElseGet(() -> this.toBuildProductColor(productColorModel, productId));
      productColorEntityList.add(productColorEntity);
    }

    productColorRepository.saveAll(productColorEntityList);
  }

  /**
   * Select all entity by product id.
   *
   * @param productId productId
   * @return list of entity
   */
  public List<ProductColorEntity> selectByProductId(Long productId) {
    return productColorRepository.selectByProductId(productId);
  }

  /**
   * Delete all entity by product id.
   *
   * @param productId productId
   */
  public void deleteByProductId(Long productId) {
    productColorRepository.deleteByProductId(productId);
  }

  /**
   * Delete all entity by product id.
   *
   * @param productColorId productColorId
   */
  public void deleteByPrimaryKey(Long productColorId) {
    productColorRepository.deleteById(productColorId);
  }

  /**
   * To build product size use create new.
   *
   * @param productColorModel productSizeModel
   * @return entity
   */
  private ProductColorEntity toBuildProductColor(ProductColorModel productColorModel,
                                                 Long productId) {
    ProductColorEntity productColorEntity = new ProductColorEntity();
    productColorEntity.setProductId(productId);
    productColorEntity.setProductColorName(productColorModel.getProductColorName());
    productColorEntity.setProductColorQuantity(productColorModel.getProductColorQuantity());
    commonService.setCommonCreatedEntity(productColorEntity);
    return productColorEntity;
  }

  /**
   * To build product color use create new.
   *
   * @param productColorEntity productColorEntity
   * @param productColorModel  productColorModel
   * @return entity
   */
  private ProductColorEntity toBuildProductColor(ProductColorEntity productColorEntity,
                                                 ProductColorModel productColorModel,
                                                 Long productId) {
    productColorEntity.setProductId(productId);
    productColorEntity.setProductColorName(productColorModel.getProductColorName());
    productColorEntity.setProductColorQuantity(productColorModel.getProductColorQuantity());
    commonService.setCommonUpdateEntity(productColorEntity);
    return productColorEntity;
  }

}
