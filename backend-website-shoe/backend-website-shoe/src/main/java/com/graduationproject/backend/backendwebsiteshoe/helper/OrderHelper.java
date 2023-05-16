package com.graduationproject.backend.backendwebsiteshoe.helper;

import com.graduationproject.backend.backendwebsiteshoe.common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.common.Constant;
import com.graduationproject.backend.backendwebsiteshoe.common.DatetimeConvertFormat;
import com.graduationproject.backend.backendwebsiteshoe.common.User;
import com.graduationproject.backend.backendwebsiteshoe.dto.EachMonthOrderDto;
import com.graduationproject.backend.backendwebsiteshoe.dto.ICart;
import com.graduationproject.backend.backendwebsiteshoe.dto.IOrder;
import com.graduationproject.backend.backendwebsiteshoe.entity.CartEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.OrderEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.UserInformationEntity;
import com.graduationproject.backend.backendwebsiteshoe.forms.CancelOrder;
import com.graduationproject.backend.backendwebsiteshoe.forms.OrderForm;
import com.graduationproject.backend.backendwebsiteshoe.forms.OrderFormSupport;
import com.graduationproject.backend.backendwebsiteshoe.model.OrderJasperModel;
import com.graduationproject.backend.backendwebsiteshoe.service.CartService;
import com.graduationproject.backend.backendwebsiteshoe.service.OrderService;
import com.graduationproject.backend.backendwebsiteshoe.service.ProductService;
import com.graduationproject.backend.backendwebsiteshoe.service.ProductSizeService;
import com.graduationproject.backend.backendwebsiteshoe.service.UserInformationService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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

  @Autowired
  ProductService productService;

  @Autowired
  ProductSizeService productSizeService;

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
    Optional<UserInformationEntity> userInformation;
    UserInformationEntity userInformationEntity = new UserInformationEntity();
    if (userId != null) {
      userInformation = userInformationService.getUserInformation(userId);
      if (userInformation.isPresent()) {
        BeanUtils.copyProperties(userInformation.get(), userInformationEntity);
      }
      userInformationEntity.setPhone(orderJasperModel.getCustomer().getCustomerPhone());
      userInformationEntity.setAddress(orderJasperModel.getCustomer().getCustomerAddress());
      userInformationEntity.setNote(orderJasperModel.getCustomer().getCustomerNote());
    } else {
      userInformationEntity =
          this.toBuildUserInformationEntity(orderJasperModel.getCustomer(), User.CUSTOMER.getCode(),
              userId);
    }

    userInformationService.insert(userInformationEntity);
    for (CartEntity cart : cartEntityList) {
      totalPrice = totalPrice.add(cart.getProductCurrentPrice().multiply(
          BigDecimal.valueOf(cart.getProductQuantity())));
    }

    OrderEntity orderEntity =
        this.toBuildOrderEntity(orderJasperModel, cartId, totalPrice, userId,
            userInformationEntity.getUserInformationId());
    for (OrderJasperModel.Order ord :
        orderJasperModel.getOrderList()) {
      productService.updateSize(ord.getProductId(), ord.getProductQuantity(), Constant.MINUS);
      productSizeService
          .updateSize(ord.getProductId(), ord.getProductSize(), ord.getProductQuantity(),
              Constant.MINUS);
    }
    try {
      cartService.insert(cartEntityList);

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
        .orderList(this.toBuildOrderSupport(orders.getContent()).stream().distinct().toList())
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
   * Get all by order id.
   *
   * @param userId userId
   * @return list entity
   */
  public List<OrderJasperModel> getAllByUserId(Long userId) {
    List<OrderJasperModel> orderJasperModelList = new ArrayList<>();
    List<IOrder> order = orderService.getAllByUserId(userId);
    List<Long> orderIdList = order.stream().map(IOrder::getOrderId).distinct().toList();
    orderIdList.forEach(o -> {
      orderJasperModelList.add(
          this.toBuildOrderJasperModel(
              order.stream().filter(ord -> ord.getOrderId().equals(o)).toList())
      );
    });

    return orderJasperModelList;
  }

  /**
   * Get all by order id.
   *
   * @param orderId     orderId
   * @param orderStatus orderStatus
   * @return list entity
   */
  public OrderEntity update(Long orderId, String orderStatus) {
    Optional<OrderEntity> orderEntity = orderService.getByOrderId(orderId);
    if (orderEntity.isEmpty()) {
      return null;
    }
    orderEntity.get().setOrderStatus(orderStatus);
    return orderService.update(orderEntity.get());
  }

  /**
   * Find all.
   *
   * @return entity list
   */
  public List<ICart> getAll() {
    return orderService.getAll();
  }

  /**
   * Find all.
   *
   * @return entity list
   */
  public List<ICart> getAllBill() {
    return orderService.findBill();
  }

  /**
   * Delete order.
   *
   * @return TRUE or FALSE
   */
  public Boolean deleteOrder(Long orderId, CancelOrder cancelOrder) {
    Boolean flag = Boolean.FALSE;
    Optional<OrderEntity> orderEntity = orderService.getByOrderId(orderId);
    List<CartEntity> cartEntityList = cartService.getByCartId(orderEntity.get().getCartId());
    cartEntityList.forEach(cart -> {
      productSizeService
          .updateSize(cart.getProductId(), cart.getProductSizeName(), cart.getProductQuantity(),
              Constant.PLUS);
      productService.updateSize(cart.getProductId(), cart.getProductQuantity(), Constant.PLUS);
//      cartService
//          .deleteAllByCartId(cart.getCartId(), cart.getProductId(), cart.getProductSizeName());
    });

    try {
      Optional<OrderEntity> entity = orderService.getByOrderId(orderId);
      OrderEntity order = new OrderEntity();
      BeanUtils.copyProperties(entity.get(), order);
      order.setOrderStatus("2");
      orderService.update(order);
      if (Objects.nonNull(cancelOrder.getUserId())) {
        Long userId = cancelOrder.getUserId();
        Optional<UserInformationEntity> userInformationEntity = userInformationService.getUserInformation(userId);
        UserInformationEntity userInfo = new UserInformationEntity();
        BeanUtils.copyProperties(userInformationEntity.get(), userInfo);
        userInfo.setNote(cancelOrder.getUserNote());
        userInformationService.update(userInfo);
      }
      flag = Boolean.TRUE;
    } catch (DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }

    return flag;
  }

  /**
   * Find all.
   *
   * @return entity list
   */
  public Collection<BigDecimal> getEachMonthOrder() {
    return this.getTotalPriceEachMonth(orderService.getEachMonthOrder());
  }

  /**
   * Find all.
   *
   * @return entity list
   */
  public List<ICart> getAllDistinct() {
    return orderService.getAllDistinct();
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
    cartEntity.setProductSizeName(cart.getProductSize());
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
    orderEntity.setOrderStatus(String.valueOf(Constant.ZERO));
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
  private List<OrderFormSupport> toBuildOrderSupport(List<IOrder> orderList) {
    return orderList.stream()
        .map(order -> OrderFormSupport.builder()
            .orderId(order.getOrderId())
            .orderCode(order.getOrderCode())
            .orderStatus(order.getStatus())
            .fullName((Objects.nonNull(order.getCustomerLastName()) ? order.getCustomerLastName() :
                Constant.EMPTY_SPACE)
                + Constant.SPACE
                + (Objects.nonNull(order.getCustomerFirstName()) ? order.getCustomerFirstName() :
                Constant.EMPTY_SPACE))
            .customerAddress(order.getCustomerAddress())
            .createdDate(order.getCreatedDate())
            .customerNote(order.getStatus().equals("2") ? order.getCustomerNote() : "")
            .userId(order.getUserId())
            .build())
        .toList();
  }

  /**
   * To build order jasper modal.
   *
   * @param orderList orderList
   * @return order jasper
   */
  private OrderJasperModel toBuildOrderJasperModel(List<IOrder> orderList) {
    OrderJasperModel orderJasperModel = new OrderJasperModel();

    if (orderList.size() <= 0) {
      orderJasperModel.setOrderList(Collections.emptyList());
      return orderJasperModel;
    }

    OrderJasperModel.Customer customer = new OrderJasperModel.Customer();
    customer.setCreatedDate(
        CollectionUtils.isEmpty(orderList) && Objects.isNull(orderList.get(0).getCreatedDate())
            ? " "
            : DatetimeConvertFormat
            .convertDateToStringWithFormat(Constant.PATTERN_DATETIME_HOURS,
                orderList.get(0).getCreatedDate()));
    customer.setCustomerAddress(orderList.get(0).getCustomerAddress());
    customer.setCustomerOrderCode(orderList.get(0).getOrderCode());
    customer.setCustomerEmail(orderList.get(0).getCustomerEmail());
    customer.setCustomerFirstName(orderList.get(0).getCustomerFirstName());
    customer.setCustomerLastName(orderList.get(0).getCustomerLastName());
    customer.setCustomerPhone(orderList.get(0).getCustomerPhone());
    customer.setUserId(orderList.get(0).getUserId());
    customer.setCustomerNote(
        Objects.nonNull(orderList.get(0).getCustomerNote()) ? orderList.get(0).getCustomerNote() :
            "");
    List<OrderJasperModel.Order> invoiceList = new ArrayList<>();
    BigDecimal totalPriceOfAllProduct = BigDecimal.ZERO;
    Integer totalQuantityOfAllProduct = 0;
    for (IOrder order : orderList) {
      OrderJasperModel.Order invoice = new OrderJasperModel.Order();
      invoice.setProductId(order.getProductId());
      invoice.setProductName(order.getProductName());
      invoice.setProductPrice(order.getProductPrice().setScale(FIXED, RoundingMode.UP));
      invoice.setProductQuantity(order.getProductQuantity());
      invoice.setProductSize(order.getProductSizeName());
      invoice.setCreatedDateOrder(order.getCreatedDateOrder());
      invoice.setStatus(order.getStatus());
      invoice.setProductTotalPrice(order.getProductPrice().multiply(
          BigDecimal.valueOf(order.getProductQuantity())).setScale(FIXED, RoundingMode.UP));
      totalPriceOfAllProduct = totalPriceOfAllProduct.add(invoice.getProductTotalPrice());
      totalQuantityOfAllProduct += totalQuantityOfAllProduct + order.getProductQuantity();
      invoiceList.add(invoice);
    }

    orderJasperModel.setOrderId(orderList.get(0).getOrderId());
    orderJasperModel.setOrderCode(orderList.get(0).getOrderCode());
    orderJasperModel.setCustomer(customer);
    orderJasperModel.setOrderList(invoiceList);
    orderJasperModel.setOrderStatus(orderList.get(0).getStatus());
    orderJasperModel.setTotalPriceOfAllProduct(
        totalPriceOfAllProduct.setScale(FIXED, RoundingMode.UP).toString());
    orderJasperModel.setTotalQuantityOfAllProduct(totalQuantityOfAllProduct.toString());
    return orderJasperModel;
  }

  /**
   * To build order price each month.
   *
   * @param eachMonthOrderDtoList eachMonthOrderDtoList
   * @return list total price month
   */
  private Collection<BigDecimal> getTotalPriceEachMonth(
      List<EachMonthOrderDto> eachMonthOrderDtoList) {
    Map<Integer, BigDecimal> listTotalPrice = new TreeMap<>();
    for (int index = 1; index <= 12; index++) {
      listTotalPrice.put(index, BigDecimal.ZERO);
    }
    for (EachMonthOrderDto each : eachMonthOrderDtoList) {
      listTotalPrice.put(Integer.parseInt(each.getMonth()),
          each.getTotalPrice().setScale(FIXED, RoundingMode.UP));
    }
    return listTotalPrice.values();
  }
}
