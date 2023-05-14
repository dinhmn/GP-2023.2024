package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.dto.EachMonthOrderDto;
import com.graduationproject.backend.backendwebsiteshoe.dto.ICart;
import com.graduationproject.backend.backendwebsiteshoe.dto.IOrder;
import com.graduationproject.backend.backendwebsiteshoe.entity.OrderEntity;
import com.graduationproject.backend.backendwebsiteshoe.repository.CartRepository;
import com.graduationproject.backend.backendwebsiteshoe.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
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

  public List<ICart> findBill() {
    return orderRepository.findBill();
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

  /**
   * Get all by order id.
   *
   * @param userId userId
   * @return list entity
   */
  public List<IOrder> getAllByUserId(Long userId) {
    return orderRepository.findAllByUserId(userId);
  }

  /**
   * Get total price each month.
   *
   * @return list dto
   */
  public List<EachMonthOrderDto> getEachMonthOrder() {
    return orderRepository.findAllByMonth();
  }

  /**
   * Find by id.
   *
   * @param orderId orderId
   * @return entity
   */
  public Optional<OrderEntity> getByOrderId(Long orderId) {
    return orderRepository.findById(orderId);
  }

  /**
   * Find all.
   *
   * @return entity list
   */
  public List<ICart> getAll() {
    return orderRepository.findAllTotal();
  }

  /**
   * Find all.
   *
   * @return entity list
   */
  public List<ICart> getAllDistinct() {
    return orderRepository.findAllTotalDistinct();
  }

  /**
   * Update by order entity.
   *
   * @param orderEntity orderId
   * @return entity
   */
  public OrderEntity update(OrderEntity orderEntity) {
    return orderRepository.save(orderEntity);
  }

  /**
   * Get order id.
   *
   * @return entity
   */
  public Long getNewOrderId() {
    return orderRepository.getNewOrderId();
  }

  public void deleteByOrderId(Long orderId) {
    orderRepository.deleteById(orderId);
  }
}
