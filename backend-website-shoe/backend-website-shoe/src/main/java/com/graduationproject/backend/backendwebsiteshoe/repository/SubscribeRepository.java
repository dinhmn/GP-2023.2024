package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.dto.ICategory;
import com.graduationproject.backend.backendwebsiteshoe.entity.SubscribeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of subscribe.
 *
 * @author Mai Ngoc Dinh.
 */
@Repository
public interface SubscribeRepository extends JpaRepository<SubscribeEntity, Long> {

  /**
   * Get all subscribe.
   *
   * @param pageable pageable
   * @return list
   */
  @Query(value = "SELECT * FROM tbl_subscribe", nativeQuery = true, countQuery =
      "SELECT COUNT(*) FROM tbl_subscribe")
  Page<SubscribeEntity> findAll(Pageable pageable);

  /**
   * Get all subscribe.
   *
   * @param subValue subValue
   * @param pageable pageable
   * @return list
   */
  @Query(value = "SELECT * FROM tbl_subscribe WHERE ?1", nativeQuery = true, countQuery =
      "SELECT COUNT(*) FROM tbl_subscribe WHERE ?1")
  Page<SubscribeEntity> findAllSubscribe(String subValue, Pageable pageable);
}
