package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.dto.ChartJS;
import com.graduationproject.backend.backendwebsiteshoe.dto.ICategory;
import com.graduationproject.backend.backendwebsiteshoe.dto.IProduct;
import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntityKey;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of category.
 *
 * @author Mai Ngoc Dinh.
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, CategoryEntityKey> {

  /**
   * Get all product.
   *
   * @param categoryId categoryId.
   * @return list of product.
   */
  @Query(value = "SELECT category.category_id AS categoryId, "
      + " trade.trademark_id AS trademarkId,"
      + " trade.trademark_name AS trademarkName, "
      + " category.category_description AS categoryDescription,"
      + " category.category_name AS categoryName, category.seo AS categorySeo, "
      + " category.created_date AS createdDate,"
      + " category.updated_date AS updatedDate, category.status AS categoryStatus"
      + " FROM tbl_trademark trade "
      + " INNER JOIN tbl_category category ON trade.trademark_id =  category.trademark_id"
      + " INNER JOIN tbl_product product ON product.category_id = category.category_id"
      + " WHERE category.category_id = ?1", nativeQuery = true)
  List<IProduct> findAllProductByCategory(String categoryId);

  /**
   * Get all category.
   *
   * @return list
   */
  @Query(value = "SELECT category.category_id AS categoryId, "
      + " trademark.trademark_id AS trademarkId,"
      + " trademark.trademark_name AS trademarkName, "
      + " category.category_description AS categoryDescription,"
      + " category.category_name AS categoryName, category.seo AS categorySeo, "
      + " category.created_date AS createdDate,"
      + " category.updated_date AS updatedDate, category.status AS categoryStatus"
      + " FROM tbl_category category"
      + " INNER JOIN tbl_trademark trademark ON category.trademark_id = trademark.trademark_id"
      + " WHERE category.category_name LIKE %?3% OR trademark.trademark_name LIKE %?3%"
      + " ORDER BY category.category_name ASC "
      + " LIMIT ?2 OFFSET ?1", nativeQuery = true)
  List<ICategory> findAllCategory(int pageNo, int pageSize, String searchValue, String sortBy);

  /**
   * Get all category.
   *
   * @return list
   */
  @Query(value = "SELECT category.category_id AS categoryId, "
      + " trademark.trademark_id AS trademarkId,"
      + " trademark.trademark_name AS trademarkName, "
      + " category.category_description AS categoryDescription,"
      + " category.category_name AS categoryName, category.seo AS categorySeo, "
      + " category.created_date AS createdDate,"
      + " category.updated_date AS updatedDate, category.status AS categoryStatus"
      + " FROM tbl_category category"
      + " INNER JOIN tbl_trademark trademark ON category.trademark_id = trademark.trademark_id"
      + " WHERE category.category_name LIKE %?2%"
      + " ORDER BY ?1",
      nativeQuery = true,
      countQuery = " SELECT count(*)"
          + " FROM tbl_category category"
          + " INNER JOIN tbl_trademark trademark ON category.trademark_id = trademark.trademark_id"
          + " WHERE category.category_name LIKE %?2%"
          + " ORDER BY ?1"
  )
  Page<ICategory> findAllCategory(String sortBy, String searchValue, Pageable pageable);

  /**
   * Get all category.
   *
   * @return list
   */
  @Query(value = "SELECT category.category_id AS categoryId, "
      + " trademark.trademark_id AS trademarkId,"
      + " trademark.trademark_name AS trademarkName, "
      + " category.category_description AS categoryDescription,"
      + " category.category_name AS categoryName, category.seo AS categorySeo, "
      + " category.created_date AS createdDate,"
      + " category.updated_date AS updatedDate, category.status AS categoryStatus"
      + " FROM tbl_category category"
      + " INNER JOIN tbl_trademark trademark ON category.trademark_id = trademark.trademark_id",
      nativeQuery = true)
  List<ICategory> findAllCategory();

  @Query(value = "SELECT category.category_id AS categoryId, "
      + " category.category_name AS categoryName,"
      + " COUNT(tp.product_id) AS countItem"
      + " FROM tbl_category category"
      + " INNER JOIN tbl_product tp on category.category_id = tp.category_id"
      + " INNER JOIN tbl_cart tc on tp.product_id = tc.product_id"
      + " GROUP BY category.category_id, category.category_name"
      + " ORDER BY category.category_id", nativeQuery = true)
  List<ChartJS> findAllChart();

}
