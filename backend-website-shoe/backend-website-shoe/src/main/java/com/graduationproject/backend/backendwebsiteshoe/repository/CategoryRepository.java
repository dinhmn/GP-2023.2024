package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.dto.IProduct;
import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, CategoryEntityKey> {

    /**
     * Get all product.
     *
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

}
