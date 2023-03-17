package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.dto.ICategory;
import com.graduationproject.backend.backendwebsiteshoe.dto.IProduct;
import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntityKey;
import com.graduationproject.backend.backendwebsiteshoe.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, CategoryEntityKey> {

    /**
     * Get all product.
     *
     * @param categoryId categoryId.
     * @return list of product.
     */
    @Query(value = "SELECT trade.trademark_name as trademarkName, category.category_id AS categoryId, "
            + " category.category_name AS categoryName, product.product_id AS productId, product.product_name AS productName, "
            + " product.product_price AS productPrice, product.product_price_sale AS productPriceSale, product.quantity AS productQuantity, "
            + " product.product_description AS productDescription, product.product_seo AS productSeo"
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
    @Query(value = "SELECT category.category_id AS categoryId, trademark.trademark_id AS trademarkId,"
            + " trademark.trademark_name AS trademarkName, category.category_description AS categoryDescription,"
            + " category.category_name AS categoryName, category.seo AS categorySeo, category.created_date AS createdDate,"
            + " category.updated_date AS updatedDate, category.status AS categoryStatus"
            + " FROM tbl_category category"
            + " INNER JOIN tbl_trademark trademark ON category.trademark_id = trademark.trademark_id"
            , nativeQuery = true)
    List<ICategory> findAllCategory();

}
