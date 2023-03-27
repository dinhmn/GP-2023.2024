package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.entity.SourceImagesEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.SourceImagesEntityKey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of source image.
 *
 * @author Mai Ngoc Dinh
 */
@Repository
public interface SourceImagesRepository
    extends JpaRepository<SourceImagesEntity, SourceImagesEntityKey> {

  /**
   * Delete all image of product.
   *
   * @param productId productId
   * @param imagesCode imagesCode
   */
  @Query(value = "DELETE FROM SourceImagesEntity source "
      + " WHERE source.productId = ?1 AND source.imageCode IN ?2")
  void deleteByProductId(Long productId, List<String> imagesCode);
}
