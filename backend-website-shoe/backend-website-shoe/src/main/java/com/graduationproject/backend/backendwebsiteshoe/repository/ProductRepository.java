package com.graduationproject.backend.backendwebsiteshoe.repository;

import java.util.List;

import com.graduationproject.backend.backendwebsiteshoe.entity.ProductEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductEntityKey;
import com.graduationproject.backend.backendwebsiteshoe.dto.IProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, ProductEntityKey> {

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
            + " INNER JOIN tbl_product product ON product.category_id = category.category_id", nativeQuery = true)
    List<IProduct> findAllProduct();


    /**
     * Get information of product.
     *
     * @return product.
     */
}
