package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.entity.ProductColorEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of product color.
 *
 * @author Mai Ngoc Dinh
 */
@Repository
public interface ProductColorRepository
    extends JpaRepository<ProductColorEntity, Long> {

  /**
   * Select all product color.
   *
   * @param productId productId
   * @return list product color.
   */
  @Query(value = "FROM ProductColorEntity color WHERE color.productId = ?1")
  List<ProductColorEntity> selectByProductId(Long productId);

  /**
   * Delete all product color.
   *
   * @param productId productId
   */
  @Query(value = "DELETE FROM ProductColorEntity color WHERE color.productId = ?1")
  void deleteByProductId(Long productId);

}
