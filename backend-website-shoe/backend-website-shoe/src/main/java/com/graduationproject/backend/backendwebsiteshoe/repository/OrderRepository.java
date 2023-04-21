package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.dto.IOrder;
import com.graduationproject.backend.backendwebsiteshoe.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of order.
 *
 * @author Mai Ngoc Dinh
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

  /**
   * Get order by order id.
   *
   * @param orderId orderId
   * @return order.
   */
  @Query(value =
      "SELECT product.productId AS productId, usr.userId AS userId, order.orderId AS orderId, "
          + " product.productName AS productName, userInfo.address AS customerAddress, "
          + " COALESCE(product.productPriceSale, product.productPrice) AS productPrice, "
          + " product.quantity AS productQuantity, cart.cartId AS cartId, userInfo.firstName AS "
          + " customerFirstName, userInfo.lastName AS customerLastName, userInfo.email AS "
          + " customerEmail, userInfo.phone AS customerPhone FROM OrderEntity order "
          + " INNER JOIN CartEntity cart ON cart.cartId = order.cartId"
          + " INNER JOIN ProductEntity product ON cart.productId = product.productId"
          + " INNER JOIN ProductColorEntity color ON color.productId = product.productId"
          + " INNER JOIN ProductSizeEntity si ON si.productId = product.productId"
          + " INNER JOIN UserInformationEntity userInfo ON userInfo.userInformationId = "
          + " order.userInformationId LEFT JOIN UserEntity usr ON usr.userId = order.userId"
          + " WHERE order.orderId = ?1")
  IOrder findAllByOrderId(Long orderId);

  /**
   * Get all order.
   *
   * @param pageable pageable
   * @return list order.
   */
  @Query(value =
      "SELECT product.product_id AS productId, usr.user_id AS userId, order.order_id AS orderId, "
          + " product.product_name AS productName, userInfo.address AS customerAddress,"
          + " COALESCE(product.product_price_sale, product.product_price) AS productPrice, "
          + " product.quantity AS productQuantity, cart.cart_id AS cartId, "
          + " userInfo.first_name AS customerFirstName, userInfo.last_name AS customerLastName, "
          + " userInfo.email AS customerEmail, userInfo.phone AS customerPhone FROM tbl_order order"
          + " INNER JOIN tbl_cart cart ON cart.cart_id = order.cart_id"
          + " INNER JOIN tbl_product product ON cart.product_id = product.product_id"
          + " INNER JOIN tbl_product_color color ON color.product_id = product.product_id"
          + " INNER JOIN tbl_product_size si ON si.product_id = product.product_id"
          + " INNER JOIN tbl_user_information userInfo ON userInfo.user_information_id = "
          + " order.user_information_id LEFT JOIN tbl_user usr ON usr.user_id = order.user_id",
      nativeQuery = true, countQuery = " SELECT COUNT(order.order_id) FROM tbl_order order"
      + " INNER JOIN tbl_cart cart ON cart.cart_id = order.cart_id"
      + " INNER JOIN tbl_product product ON cart.product_id = product.product_id"
      + " INNER JOIN tbl_product_color color ON color.product_id = product.product_id"
      + " INNER JOIN tbl_product_size si ON si.product_id = product.product_id"
      + " INNER JOIN tbl_user_information userInfo ON userInfo.user_information_id = "
      + " order.user_information_id LEFT JOIN tbl_user usr ON usr.user_id = order.user_id")
  Page<IOrder> findAllPage(Pageable pageable);
}
