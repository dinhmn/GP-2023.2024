package com.graduationproject.backend.backendwebsiteshoe.helper;

import com.graduationproject.backend.backendwebsiteshoe.common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.common.Constant;
import com.graduationproject.backend.backendwebsiteshoe.common.DatetimeConvertFormat;
import com.graduationproject.backend.backendwebsiteshoe.common.User;
import com.graduationproject.backend.backendwebsiteshoe.dto.IOrder;
import com.graduationproject.backend.backendwebsiteshoe.entity.CartEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.OrderEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.UserInformationEntity;
import com.graduationproject.backend.backendwebsiteshoe.forms.OrderForm;
import com.graduationproject.backend.backendwebsiteshoe.model.OrderJasperModel;
import com.graduationproject.backend.backendwebsiteshoe.service.CartService;
import com.graduationproject.backend.backendwebsiteshoe.service.OrderService;
import com.graduationproject.backend.backendwebsiteshoe.service.UserInformationService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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

  private static final Integer FIXED = 0;

  @Autowired
  CommonService commonService;

  @Autowired
  OrderService orderService;

  @Autowired
  CartService cartService;

  @Autowired
  UserInformationService userInformationService;

  /**
   * Insert order into database.
   *
   * @param orderJasperModel orderJasperModel
   * @return entity
   */
  public OrderEntity insert(OrderJasperModel orderJasperModel) {
    Long cartId = cartService.getMaxCartId() + 1;
    Long userId = orderJasperModel.getCustomer().getUserId();
    BigDecimal totalPrice = BigDecimal.ZERO;
    List<CartEntity> cartEntityList = orderJasperModel.getOrderList().stream()
        .map(cart -> this.toBuildCartEntity(cart, cartId))
        .toList();
    UserInformationEntity userInformationEntity =
        this.toBuildUserInformationEntity(orderJasperModel.getCustomer(), userId == null
            ? User.CUSTOMER.getCode()
            : User.USER.getCode(), userId);
    for (CartEntity cart : cartEntityList) {
      totalPrice = totalPrice.add(cart.getProductCurrentPrice().multiply(
          BigDecimal.valueOf(cart.getProductQuantity())));
    }

    OrderEntity orderEntity =
        this.toBuildOrderEntity(orderJasperModel, cartId, totalPrice, userId,
            userInformationEntity.getUserInformationId());

    try {
      cartService.insert(cartEntityList);
      userInformationService.insert(userInformationEntity);
      return orderService.insert(orderEntity);
    } catch (DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }

    return null;
  }

  /**
   * Get all by order.
   *
   * @param pageNo        pageNo
   * @param pageSize      pageSize
   * @param sortBy        sortBy
   * @param sortDirection sortDirection
   * @param searchValue   searchValue
   * @return list entity
   */
  public OrderForm getAllByOrderId(int pageNo, int pageSize, String sortBy, String sortDirection,
                                   String searchValue) {
    Pageable pageable = commonService.setPageable(pageSize, pageNo, sortBy, sortDirection);

    // Get content for page object
    Page<IOrder> orders = orderService.getAllByOrderId(pageable, searchValue);

    return OrderForm.builder()
        .orderList(orders.getContent())
        .pageNo(orders.getNumber())
        .pageSize(orders.getSize())
        .totalElements(orders.getTotalElements())
        .totalPages(orders.getTotalPages())
        .last(orders.isLast())
        .build();
  }

  /**
   * Get all by order id.
   *
   * @param orderId orderId
   * @return list entity
   */
  public OrderJasperModel getAllByOrderId(Long orderId) {
    List<IOrder> order = orderService.getAllByOrderId(orderId);
    return this.toBuildOrderJasperModel(order);
  }

  /**
   * To build cart.
   *
   * @param cart   cart
   * @param cartId cartId
   * @return entity
   */
  private CartEntity toBuildCartEntity(OrderJasperModel.Order cart, Long cartId) {
    CartEntity cartEntity = new CartEntity();
    cartEntity.setCartId(cartId);
    cartEntity.setProductId(cart.getProductId());
    cartEntity.setProductCurrentPrice(cart.getProductPrice());
    cartEntity.setProductName(cart.getProductName());
    cartEntity.setProductQuantity(cart.getProductQuantity());
    commonService.setCommonCreatedEntity(cartEntity);
    return cartEntity;
  }

  /**
   * Insert order into database.
   *
   * @param orderJasperModel  orderJasperModel
   * @param cartId            cartId
   * @param orderTotalPrice   orderTotalPrice
   * @param userId            userId
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

  /**
   * Insert user information into database.
   *
   * @param customer customer
   * @param userCode userCode
   * @return entity
   */
  private UserInformationEntity toBuildUserInformationEntity(OrderJasperModel.Customer customer,
                                                             String userCode, Long userId) {
    UserInformationEntity userInformationEntity = new UserInformationEntity();
    userInformationEntity.setUserId(userId);
    userInformationEntity.setAddress(customer.getCustomerAddress());
    userInformationEntity.setPhone(customer.getCustomerPhone());
    userInformationEntity.setFirstName(customer.getCustomerFirstName());
    userInformationEntity.setLastName(customer.getCustomerLastName());
    userInformationEntity.setUserCode(userCode);
    userInformationEntity.setEmail(customer.getCustomerEmail());
    commonService.setCommonCreatedEntity(userInformationEntity);
    return userInformationEntity;
  }

  /**
   * To build order jasper modal.
   *
   * @param orderList orderList
   * @return order jasper
   */
  private OrderJasperModel toBuildOrderJasperModel(List<IOrder> orderList) {
    OrderJasperModel orderJasperModel = new OrderJasperModel();

    OrderJasperModel.Customer customer = new OrderJasperModel.Customer();
    customer.setCreatedDate(
        Objects.isNull(orderList.get(0).getCreatedDate())
            ? " "
            : DatetimeConvertFormat
            .convertDateToStringWithFormat(Constant.PATTERN_DATETIME_HOURS, orderList.get(0).getCreatedDate()));
    customer.setCustomerAddress(orderList.get(0).getCustomerAddress());
    customer.setCustomerOrderCode(orderList.get(0).getOrderCode());
    customer.setCustomerEmail(orderList.get(0).getCustomerEmail());
    customer.setCustomerFirstName(orderList.get(0).getCustomerFirstName());
    customer.setCustomerLastName(orderList.get(0).getCustomerLastName());
    customer.setCustomerPhone(orderList.get(0).getCustomerPhone());
    customer.setUserId(orderList.get(0).getUserId());
    customer.setCustomerNote(Objects.nonNull(orderList.get(0).getCustomerNote()) ? orderList.get(0).getCustomerNote() : "");
    List<OrderJasperModel.Order> invoiceList = new ArrayList<>();
    BigDecimal totalPriceOfAllProduct = BigDecimal.ZERO;
    Integer totalQuantityOfAllProduct = 0;
    for (IOrder order : orderList) {
      OrderJasperModel.Order invoice = new OrderJasperModel.Order();
      invoice.setProductId(order.getProductId());
      invoice.setProductName(order.getProductName());
      invoice.setProductPrice(order.getProductPrice().setScale(FIXED, RoundingMode.UP));
      invoice.setProductQuantity(order.getProductQuantity());
      invoice.setProductTotalPrice(order.getProductPrice().multiply(
          BigDecimal.valueOf(order.getProductQuantity())));
      totalPriceOfAllProduct = totalPriceOfAllProduct.add(order.getProductPrice());
      totalQuantityOfAllProduct += totalQuantityOfAllProduct + order.getProductQuantity();
      invoiceList.add(invoice);
    }

    orderJasperModel.setOrderId(orderList.get(0).getOrderId());
    orderJasperModel.setOrderCode(orderList.get(0).getOrderCode());
    orderJasperModel.setCustomer(customer);
    orderJasperModel.setOrderList(invoiceList);
    orderJasperModel.setTotalPriceOfAllProduct(
        totalPriceOfAllProduct.setScale(FIXED, RoundingMode.UP).doubleValue() + " đ");
    orderJasperModel.setTotalQuantityOfAllProduct(totalQuantityOfAllProduct.toString());
    return orderJasperModel;
  }
}
