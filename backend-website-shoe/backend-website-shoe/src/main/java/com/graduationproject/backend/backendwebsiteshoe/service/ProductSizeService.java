package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.Common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.Common.Constant;
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
   * @return entity
   */
  public ProductSizeEntity insert(ProductSizeModel productSizeModel) {
    ProductSizeEntity productSizeEntity = this.toBuildProductSize(productSizeModel);
    return productSizeRepository.save(productSizeEntity);
  }

  /**
   * Insert all product size image use create new.
   *
   * @param productSizeModelList productSizeModelList
   * @return entity of list
   */
  public List<ProductSizeEntity> insertAll(List<ProductSizeModel> productSizeModelList) {
    List<ProductSizeEntity> productSizeEntityList = productSizeModelList.stream()
        .map(this::toBuildProductSize)
        .toList();
    return productSizeRepository.saveAll(productSizeEntityList);
  }

  /**
   * Insert product size image use create new.
   *
   * @param productSizeModel productSizeModel
   * @return entity
   */
  public ProductSizeEntity update(ProductSizeModel productSizeModel) {
    ProductSizeEntityKey productSizeEntityKey = new ProductSizeEntityKey();
    productSizeEntityKey.setPk(productSizeModel.getProductSizeId());
    Optional<ProductSizeEntity> productSize = productSizeRepository.findById(productSizeEntityKey);

    if (productSize.isPresent()) {
      ProductSizeEntity productSizeEntity =
          this.toBuildProductSize(productSize.get(), productSizeModel);
      return productSizeRepository.save(productSizeEntity);
    }

    ProductSizeEntity productSizeEntity = this.toBuildProductSize(productSizeModel);
    return productSizeRepository.save(productSizeEntity);
  }

  /**
   * To build product size image use create new.
   *
   * @param productSizeModel productSizeModel
   * @return entity
   */
  private ProductSizeEntity toBuildProductSize(ProductSizeModel productSizeModel) {
    ProductSizeEntity productSizeEntity = new ProductSizeEntity();
    productSizeEntity.setProductId(productSizeModel.getProductSizeId());
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
   * @return entity
   */
  private ProductSizeEntity toBuildProductSize(ProductSizeEntity productSizeEntity,
                                               ProductSizeModel productSizeModel) {
    productSizeEntity.setProductId(productSizeModel.getProductSizeId());
    productSizeEntity.setProductSizeName(productSizeModel.getProductSizeName());
    productSizeEntity.setProductSizeQuantity(productSizeModel.getProductSizeQuantity());
    commonService.setCommonUpdateEntity(productSizeEntity);
    return productSizeEntity;
  }

}
