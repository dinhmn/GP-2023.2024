package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductSizeEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductSizeEntityKey;
import com.graduationproject.backend.backendwebsiteshoe.model.ProductSizeModel;
import com.graduationproject.backend.backendwebsiteshoe.repository.ProductSizeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implement service of item product size.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class ProductSizeService {

  @Autowired
  CommonService commonService;

  @Autowired
  ProductSizeRepository productSizeRepository;

  /**
   * Insert product size image use create new.
   *
   * @param productSizeModel productSizeModel
   * @param productId        productId
   * @return entity
   */
  public ProductSizeEntity insert(ProductSizeModel productSizeModel, Long productId) {
    ProductSizeEntity productSizeEntity = this.toBuildProductSize(productSizeModel, productId);
    return productSizeRepository.save(productSizeEntity);
  }

  /**
   * Insert all product size image use create new.
   *
   * @param productSizeModelList productSizeModelList
   * @param productId            productId
   */
  public void insertAll(List<ProductSizeModel> productSizeModelList, Long productId) {
    List<ProductSizeEntity> productSizeEntityList = productSizeModelList.stream()
        .map(size -> this.toBuildProductSize(size, productId))
        .toList();
    productSizeRepository.saveAll(productSizeEntityList);
  }

  /**
   * Insert product size image use create new.
   *
   * @param productSizeModel productSizeModel
   * @return entity
   */
  public ProductSizeEntity update(ProductSizeModel productSizeModel, Long productId) {
    ProductSizeEntityKey productSizeEntityKey = new ProductSizeEntityKey();
    productSizeEntityKey.setPk(productSizeModel.getProductSizeId());
    Optional<ProductSizeEntity> productSize = productSizeRepository.findById(productSizeEntityKey);

    if (productSize.isPresent()) {
      ProductSizeEntity productSizeEntity =
          this.toBuildProductSize(productSize.get(), productSizeModel, productId);
      return productSizeRepository.save(productSizeEntity);
    }

    ProductSizeEntity productSizeEntity = this.toBuildProductSize(productSizeModel, productId);
    return productSizeRepository.save(productSizeEntity);
  }

  /**
   * Insert product size image use create new.
   *
   * @param productSizeModelList productSizeModelList
   * @param productId            productId
   */
  public void updateAll(List<ProductSizeModel> productSizeModelList,
                                           Long productId) {
    List<ProductSizeEntity> productSizeEntityList = new ArrayList<>();

    for (ProductSizeModel productSizeModel : productSizeModelList) {
      ProductSizeEntityKey productSizeEntityKey = new ProductSizeEntityKey();
      productSizeEntityKey.setPk(productSizeModel.getProductSizeId());
      Optional<ProductSizeEntity> productSize =
          productSizeRepository.findById(productSizeEntityKey);

      ProductSizeEntity productSizeEntity;
      productSizeEntity = productSize
          .map(colorEntity -> this.toBuildProductSize(colorEntity, productSizeModel, productId))
          .orElseGet(() -> this.toBuildProductSize(productSizeModel, productId));
      productSizeEntityList.add(productSizeEntity);
    }

    productSizeRepository.saveAll(productSizeEntityList);
  }

  /**
   * Insert product size image use create new.
   *
   * @param productId productId
   */
  public void deleteByProductId(Long productId) {
    productSizeRepository.deleteByProductId(productId);
  }

  /**
   * To build product size image use create new.
   *
   * @param productSizeModel productSizeModel
   * @param productId        productId
   * @return entity
   */
  private ProductSizeEntity toBuildProductSize(ProductSizeModel productSizeModel, Long productId) {
    ProductSizeEntity productSizeEntity = new ProductSizeEntity();
    productSizeEntity.setProductId(productId);
    productSizeEntity.setProductSizeName(productSizeModel.getProductSizeName());
    productSizeEntity.setProductSizeQuantity(productSizeModel.getProductSizeQuantity());
    commonService.setCommonCreatedEntity(productSizeEntity);
    return productSizeEntity;
  }

  /**
   * To build product size image use create new.
   *
   * @param productSizeEntity productSizeEntity
   * @param productSizeModel  productSizeModel
   * @param productId         productId
   * @return entity
   */
  private ProductSizeEntity toBuildProductSize(ProductSizeEntity productSizeEntity,
                                               ProductSizeModel productSizeModel,
                                               Long productId) {
    productSizeEntity.setProductId(productId);
    productSizeEntity.setProductSizeName(productSizeModel.getProductSizeName());
    productSizeEntity.setProductSizeQuantity(productSizeModel.getProductSizeQuantity());
    commonService.setCommonUpdateEntity(productSizeEntity);
    return productSizeEntity;
  }

}
