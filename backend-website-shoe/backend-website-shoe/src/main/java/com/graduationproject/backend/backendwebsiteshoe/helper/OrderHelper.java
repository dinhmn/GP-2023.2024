package com.graduationproject.backend.backendwebsiteshoe.helper;

import com.graduationproject.backend.backendwebsiteshoe.common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.dto.IOrder;
import com.graduationproject.backend.backendwebsiteshoe.entity.CartEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.OrderEntity;
import com.graduationproject.backend.backendwebsiteshoe.model.OrderJasperModel;
import com.graduationproject.backend.backendwebsiteshoe.service.CartService;
import com.graduationproject.backend.backendwebsiteshoe.service.OrderService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * Implement helper of order helper.
 *
 * @author Mai Ngoc Dinh
 */
@Component
public class OrderHelper {

  @Autowired
  CommonService commonService;

  @Autowired
  OrderService orderService;

  @Autowired
  CartService cartService;

  /**
   * Insert order into database.
   *
   * @param orderJasperModel orderJasperModel
   * @return entity
   */
  public OrderEntity insert(OrderJasperModel orderJasperModel) {
    Long cartId = cartService.getMaxCartId() + 1;
    OrderEntity orderEntity = this.toBuildOrderEntity(orderJasperModel, cartId);
    List<CartEntity> cartEntityList = orderJasperModel.getOrderList().stream()
        .map(cart -> this.toBuildCartEntity(cart, cartId))
        .toList();

    try {
      cartService.insert(cartEntityList);
    } catch (DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }

    return orderService.insert(orderEntity);
  }

  /**
   * Get all by order.
   *
   * @param pageable pageable
   * @return list entity
   */
  public Page<IOrder> getAllByOrderId(Pageable pageable) {
    return orderRepository.findAllPage(pageable);
  }

  /**
   * Get all by order id.
   *
   * @param orderId orderId
   * @return list entity
   */
  public IOrder getAllByOrderId(Long orderId) {
    return orderRepository.findAllByOrderId(orderId);
  }

  /**
   * To build cart.
   *
   * @param cart cart
   * @param cartId cartId
   * @return entity
   */
  private CartEntity toBuildCartEntity(OrderJasperModel.Order cart, Long cartId) {
    CartEntity cartEntity = new CartEntity();
    cartEntity.setCartId(cartId);
    cartEntity.setProductId(cart.getProductId());
    cartEntity.setProductCurrentPrice(BigDecimal.valueOf(cart.getProductPrice()));
    cartEntity.setProductName(cart.getProductName());
    cartEntity.setProductQuantity(cart.getProductQuantity());
    commonService.setCommonCreatedEntity(cartEntity);
    return cartEntity;
  }

  /**
   * Insert order into database.
   *
   * @param orderJasperModel orderJasperModel
   * @param cartId cartId
   * @param orderTotalPrice orderTotalPrice
   * @param userId userId
   * @param userInformationId userInformationId
   * @return entity
   */
  private OrderEntity toBuildOrderEntity(OrderJasperModel orderJasperModel, Long cartId,
                                         BigDecimal orderTotalPrice, Long userId,
                                         Long userInformationId) {
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setOrderCode(String.valueOf((new Date()).getTime()));
    orderEntity.setOrderStatus(Boolean.FALSE);
    orderEntity.setCartId(cartId);
    orderEntity.setOrderTotalPrice(orderTotalPrice);
    orderEntity.setUserId(userId);
    orderEntity.setUserInformationId(userInformationId);
    commonService.setCommonCreatedEntity(orderEntity);
    return orderEntity;
  }
}
