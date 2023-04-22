package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.dto.IOrder;
import com.graduationproject.backend.backendwebsiteshoe.entity.OrderEntity;
import com.graduationproject.backend.backendwebsiteshoe.repository.CartRepository;
import com.graduationproject.backend.backendwebsiteshoe.repository.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implement service of message.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class OrderService {

  @Autowired
  CartRepository cartRepository;

  @Autowired
  OrderRepository orderRepository;

  public Long getMaxCartId() {
    return cartRepository.findMaxOrderId();
  }

  /**
   * Insert order into database.
   *
   * @param orderEntity orderEntity
   * @return entity
   */
  public OrderEntity insert(OrderEntity orderEntity) {
    return orderRepository.save(orderEntity);
  }

  /**
   * Get all by order.
   *
   * @param pageable pageable
   * @param searchValue searchValue
   * @return list entity
   */
  public Page<IOrder> getAllByOrderId(Pageable pageable, String searchValue) {
    return orderRepository.findAllPage(pageable, searchValue);
  }

  /**
   * Get all by order id.
   *
   * @param orderId orderId
   * @return list entity
   */
  public List<IOrder> getAllByOrderId(Long orderId) {
    return orderRepository.findAllByOrderId(orderId);
  }

}