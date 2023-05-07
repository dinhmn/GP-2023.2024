package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.dto.IOneProduct;
import com.graduationproject.backend.backendwebsiteshoe.dto.IProduct;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductEntityKey;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of product.
 *
 * @author Mai Ngoc Dinh
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, ProductEntityKey> {

  /**
   * Get all product.
   *
   * @return list of product.
   */
  @Query(value =
      "SELECT trade.trademark_name as trademarkName, "
          + " category.category_id AS categoryId, "
          + " category.category_name AS categoryName, "
          + " product.product_id AS productId, "
          + " product.product_name AS productName, "
          + " product.product_price AS productPrice, "
          + " product.product_price_sale AS productPriceSale, "
          + " product.quantity AS productQuantity, "
          + " product.product_description AS productDescription, "
          + " product.product_seo AS productSeo"
          + " FROM tbl_trademark trade "
          + " INNER JOIN tbl_category category ON trade.trademark_id =  category.trademark_id"
          + " INNER JOIN tbl_product product ON product.category_id = category.category_id",
      nativeQuery = true)
  List<IProduct> findAllProduct();

  /**
   * Get all product.
   *
   * @param categoryId categoryId
   * @param limitItem limitItem
   * @return list of product.
   */
  @Query(value =
      "SELECT trade.trademark_name as trademarkName, "
          + " category.category_id AS categoryId, "
          + " category.category_name AS categoryName, "
          + " product.product_id AS productId, "
          + " product.product_name AS productName, "
          + " product.product_price AS productPrice, "
          + " product.product_price_sale AS productPriceSale, "
          + " product.quantity AS productQuantity, "
          + " product.product_description AS productDescription, "
          + " image.image_id AS imageId, "
          + " image.file_name AS fileName, "
          + " image.file_type AS fileType, "
          + " image.data AS fileSize, "
          + " image.image_code AS fileCode,"
          + " product.product_seo AS productSeo"
          + " FROM tbl_trademark trade "
          + " INNER JOIN tbl_category category ON trade.trademark_id =  category.trademark_id"
          + " INNER JOIN tbl_product product ON product.category_id = category.category_id"
          + " LEFT JOIN tbl_source_images image ON product.product_id = image.product_id AND image.image_code = 31"
          + " WHERE category.category_id = ?1"
          + " ORDER BY product.product_id"
          + " LIMIT ?2",
      nativeQuery = true)
  List<IProduct> findAllProduct(Long categoryId, Integer limitItem);

  /**
   * Get all product.
   *
   * @param limitItem limitItem
   * @return list of product.
   */
  @Query(value =
      "SELECT trade.trademark_name as trademarkName, "
          + " category.category_id AS categoryId, "
          + " category.category_name AS categoryName, "
          + " product.product_id AS productId, "
          + " product.product_name AS productName, "
          + " product.product_price AS productPrice, "
          + " product.product_price_sale AS productPriceSale, "
          + " product.quantity AS productQuantity, "
          + " product.product_description AS productDescription, "
          + " product.product_seo AS productSeo,"
          + " image.image_id AS imageId, "
          + " image.file_name AS fileName, "
          + " image.file_type AS fileType, "
          + " image.data AS fileSize, "
          + " image.image_code AS fileCode,"
          + " (product.product_price_sale - product.product_price) AS sale"
          + " FROM tbl_trademark trade "
          + " INNER JOIN tbl_category category ON trade.trademark_id =  category.trademark_id"
          + " INNER JOIN tbl_product product ON product.category_id = category.category_id"
          + " LEFT JOIN tbl_source_images image ON product.product_id = image.product_id AND image.image_code = 31"
          + " WHERE product.product_price_sale IS NOT NULL"
          + " ORDER BY sale, product.product_id LIMIT ?1",
      nativeQuery = true)
  List<IProduct> findAllProductSale(Integer limitItem);

  /**
   * Get all product.
   *
   * @param pageable pageable
   * @return list of product.
   */
  @Query(value = "SELECT DISTINCT(product.product_id) AS productId,"
      + " category.category_id AS categoryId, "
      + " category.category_name AS categoryName, "
      + " trade.trademark_name as trademarkName, "
      + " product.product_name AS productName, "
      + " product.product_price AS productPrice, "
      + " product.product_price_sale AS productPriceSale, "
      + " product.quantity AS productQuantity, "
      + " product.product_description AS productDescription, "
      + " product.product_seo AS productSeo,"
      + " image.image_id AS imageId, "
      + " image.file_name AS fileName, "
      + " image.file_type AS fileType, "
      + " image.data AS fileSize, "
      + " image.image_code AS fileCode"
      + " FROM tbl_product product "
      + " INNER JOIN tbl_category category ON product.category_id = category.category_id"
      + " INNER JOIN tbl_trademark trade ON trade.trademark_id =  category.trademark_id"
      + " LEFT JOIN tbl_source_images image ON product.product_id = image.product_id AND image.image_code = 31"
      + " WHERE product.product_name LIKE %?1% OR category_name LIKE %?1% OR product.product_price LIKE %?1%",
      nativeQuery = true, countQuery = "SELECT COUNT(*) "
      + " FROM tbl_product product "
      + " INNER JOIN tbl_category category ON product.category_id = category.category_id"
      + " INNER JOIN tbl_trademark trade ON trade.trademark_id =  category.trademark_id"
      + " LEFT JOIN tbl_source_images image ON product.product_id = image.product_id AND image.image_code = 31"
      + " WHERE product.product_name LIKE %?1% OR category_name LIKE %?1% OR product.product_price LIKE %?1%")
  Page<IProduct> findAllProduct(String searchValue, Pageable pageable);

  /**
   * Get all product.
   *
   * @param pageable pageable
   * @return list of product.
   */
  @Query(value = "SELECT trade.trademark_name as trademarkName, "
      + " category.category_id AS categoryId, "
      + " category.category_name AS categoryName, "
      + " product.product_id AS productId, "
      + " product.product_name AS productName, "
      + " product.product_price AS productPrice, "
      + " product.product_price_sale AS productPriceSale, "
      + " product.quantity AS productQuantity, "
      + " product.product_description AS productDescription, "
      + " product.product_seo AS productSeo,"
      + " image.image_id AS imageId, "
      + " image.file_name AS fileName, "
      + " image.file_type AS fileType, "
      + " image.data AS fileSize, "
      + " image.image_code AS fileCode"
      + " FROM tbl_product product "
      + " INNER JOIN tbl_category category ON product.category_id = category.category_id"
      + " INNER JOIN tbl_trademark trade ON trade.trademark_id =  category.trademark_id"
      + " LEFT JOIN tbl_source_images image ON product.product_id = image.product_id AND image.image_code = 31"
      + " WHERE product.category_id = ?1",
      nativeQuery = true, countQuery = "SELECT COUNT(*) "
      + " FROM tbl_product product "
      + " INNER JOIN tbl_category category ON product.category_id = category.category_id"
      + " INNER JOIN tbl_trademark trade ON trade.trademark_id =  category.trademark_id"
      + " LEFT JOIN tbl_source_images image ON product.product_id = image.product_id  AND image.image_code = 31"
      +
      " WHERE product.category_id = ?1")
  Page<IProduct> findAllProduct(Long categoryId, Pageable pageable);

  /**
   * Get all product.
   *
   * @param pageable pageable
   * @return list of product.
   */
  @Query(value = "SELECT trade.trademark_name as trademarkName, "
      + " category.category_id AS categoryId, "
      + " category.category_name AS categoryName, "
      + " product.product_id AS productId, "
      + " product.product_name AS productName, "
      + " product.product_price AS productPrice, "
      + " product.product_price_sale AS productPriceSale, "
      + " product.quantity AS productQuantity, "
      + " product.product_description AS productDescription, "
      + " product.product_seo AS productSeo,"
      + " image.image_id AS imageId, "
      + " image.file_name AS fileName, "
      + " image.file_type AS fileType, "
      + " image.data AS fileSize, "
      + " image.image_code AS fileCode"
      + " FROM tbl_product product "
      + " INNER JOIN tbl_category category ON product.category_id = category.category_id"
      +
      " AND ((?2 IS NOT NULL AND category.category_id = ?2) OR (?2 IS NULL AND category.category_id IS NOT NULL))"
      + " INNER JOIN tbl_trademark trade ON trade.trademark_id =  category.trademark_id"
      + " INNER JOIN tbl_product_size sie ON sie.product_id = product.product_id"
      + " INNER JOIN tbl_product_color color ON color.product_id = product.product_id"
      + " LEFT OUTER JOIN tbl_source_images image ON product.product_id = image.product_id"
      +
      " WHERE (product.product_name LIKE %?1% OR category.category_name LIKE %?1% OR product.product_price"
      + " LIKE %?1%) AND (product.product_price_sale >= ?3 AND product.product_price_sale <= ?4"
      + " AND product.product_price_sale IS NOT NULL) OR (product.product_price >= ?3"
      + " AND product.product_price <=?4 AND product.product_price IS NOT NULL)"
      +
      " AND ((?2 IS NOT NULL AND category.category_id = ?2) OR (?2 IS NULL AND category.category_id IS NOT NULL))"
      + " AND sie.product_size_name IN ?5"
      + " AND color.product_color_name IN ?6",
      nativeQuery = true, countQuery = "SELECT COUNT(*) "
      + " FROM tbl_product product "
      + " INNER JOIN tbl_category category ON product.category_id = category.category_id"
      +
      " AND ((?2 IS NOT NULL AND category.category_id = ?2) OR (?2 IS NULL AND category.category_id IS NOT NULL))"
      + " INNER JOIN tbl_trademark trade ON trade.trademark_id =  category.trademark_id"
      + " INNER JOIN tbl_product_size sie ON sie.product_id = product.product_id"
      + " INNER JOIN tbl_product_color color ON color.product_id = product.product_id"
      + " LEFT OUTER JOIN tbl_source_images image ON product.product_id = image.product_id"
      +
      " WHERE (product.product_name LIKE %?1% OR category.category_name LIKE %?1% OR product.product_price"
      + " LIKE %?1%) AND ((product.product_price_sale >= ?3 AND product.product_price_sale <= ?4"
      + " AND product.product_price_sale IS NOT NULL) OR (product.product_price >= ?3"
      + " AND product.product_price <=?4 AND product.product_price IS NOT NULL))"
      + " AND sie.product_size_name IN ?5"
      + " AND color.product_color_name IN ?6")
  Page<IProduct> findAllProduct(String searchValue, Long categoryId, BigDecimal priceMin,
                                BigDecimal priceMax, List<String> sizeList, List<String> colorList,
                                Pageable pageable);

  /**
   * Get information of product.
   *
   * @return product.
   */
  @Query(value = "SELECT category.category_id AS categoryId, "
      + " category.category_name AS categoryName, "
      + " product.product_id AS productId, "
      + "  product.product_name AS productName, "
      + " product.product_price AS productPrice, "
      + " product.product_price_sale AS productPriceSale, "
      + " product.quantity AS productQuantity, "
      + " product.product_description AS productDescription, "
      + " product.status AS productStatus, "
      + " product.product_seo AS productSeo, "
      + " color.product_color_id AS productColorId, "
      + " color.product_color_name AS productColorName, "
      + " color.product_color_quantity AS productColorQuantity, "
      + " productSize.product_size_id AS productSizeId, "
      + " image.image_id AS imageId, "
      + " image.file_name AS fileName, "
      + " image.file_type AS fileType, "
      + " image.data AS fileSize, "
      + " image.image_code AS fileCode,"
      + " productSize.product_size_name AS productSizeName,"
      + "  productSize.product_size_quantity AS productSizeQuantity"
      + " FROM tbl_category category"
      + " INNER JOIN tbl_product product ON product.category_id = category.category_id"
      + " LEFT JOIN tbl_product_color color ON product.product_id = color.product_id"
      + " LEFT JOIN tbl_product_size productSize ON product.product_id = productSize.product_id"
      + " LEFT JOIN tbl_source_images image ON product.product_id = image.product_id"
      + " WHERE product.product_id = ?1 AND product.category_id = ?2", nativeQuery = true)
  List<IOneProduct> findProductByKey(Long productId, Long categoryId);


  /**
   * Get information of product.
   *
   * @return product.
   */
  @Query(value = "SELECT "
      + " MAX(product.product_id) AS productId "
      + " FROM tbl_product product", nativeQuery = true)
  Long getProductIdLast();
}
