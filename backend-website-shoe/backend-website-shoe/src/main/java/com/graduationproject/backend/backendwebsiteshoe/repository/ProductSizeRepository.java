package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.entity.ProductSizeEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductSizeEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of product size.
 *
 * @author Mai Ngoc Dinh
 */
@Repository
public interface ProductSizeRepository
    extends JpaRepository<ProductSizeEntity, ProductSizeEntityKey> {

  /**
   * Delete all product size.
   *
   * @param productId productId
   */
  @Query(value = "DELETE FROM ProductSizeEntity productSize WHERE productSize.productId = ?1")
  void deleteByProductId(Long productId);
}
