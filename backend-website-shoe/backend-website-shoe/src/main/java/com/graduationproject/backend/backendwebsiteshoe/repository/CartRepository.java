package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.entity.CartEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.CartEntityKey;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of cart.
 *
 * @author Mai Ngoc Dinh
 */
@Repository
public interface CartRepository extends JpaRepository<CartEntity, CartEntityKey> {

  /**
   * Get max order id.
   *
   * @return order.
   */
  @Query(value = "SELECT MAX(order_id) FROM tbl_order", nativeQuery = true)
  Optional<Long> findMaxOrderId();

  List<CartEntity> findByCartId(Long cartId);
}
