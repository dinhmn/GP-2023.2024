package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.entity.CartEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.OrderEntity;
import com.graduationproject.backend.backendwebsiteshoe.repository.CartRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implement service of message.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class CartService {

  @Autowired
  CartRepository cartRepository;

  public Long getMaxCartId() {
    return cartRepository.findMaxOrderId();
  }

  /**
   * Insert cart into database.
   *
   * @param cartEntityList cartEntityList
   * @return entity
   */
  public List<CartEntity> insert(List<CartEntity> cartEntityList) {
    return cartRepository.saveAll(cartEntityList);
  }

  /**
   * Get all.
   *
   * @return entity
   */
  public List<CartEntity> getAll() {
    return cartRepository.findAll();
  }
}
