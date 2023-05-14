package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.entity.ProductSizeEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implement repository of product size.
 *
 * @author Mai Ngoc Dinh
 */
@Repository
public interface ProductSizeRepository
    extends JpaRepository<ProductSizeEntity, Long> {

  /**
   * Delete all product size.
   *
   * @param productId productId
   */
  @Query(value = "DELETE FROM ProductSizeEntity productSize WHERE productSize.productId = ?1")
  void deleteByProductId(Long productId);

  /**
   * Select all product size.
   *
   * @param productId productId
   */
  @Query(value = "FROM ProductSizeEntity productSize WHERE productSize.productId = ?1")
  List<ProductSizeEntity> selectByPrimaryKey(Long productId);

  Optional<ProductSizeEntity> findByProductIdAndProductSizeName(Long productId,
                                                                String productSizeName);
}
