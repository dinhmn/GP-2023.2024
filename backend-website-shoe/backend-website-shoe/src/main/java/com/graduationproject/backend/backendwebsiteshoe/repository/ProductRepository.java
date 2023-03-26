package com.graduationproject.backend.backendwebsiteshoe.repository;

import java.util.List;

import com.graduationproject.backend.backendwebsiteshoe.dto.IOneProduct;
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
    @Query(value = "SELECT category.category_id AS categoryId, category.category_name AS categoryName, "
            + " product.product_id AS productId, product.product_name AS productName, product.product_price AS productPrice, "
            + " product.product_price_sale AS productPriceSale, product.quantity AS productQuantity, "
            + " product.product_description AS productDescription, product.status AS productStatus, "
            + " product.product_seo AS productSeo, color.product_color_id AS productColorId, color.product_color_name AS productColorName, "
            + " color.product_color_quantity AS productColorQuantity, size.product_size_id AS productSizeId, "
            + " image.image_id AS imageId, image.file_name AS fileName, image.file_type AS fileType, "
            + " image.data AS fileSize, image.image_code AS fileCode"
            + " size.product_size_name AS productSizeName, size.product_size_quantity AS productSizeQuantity"
            + " FROM tbl_category category"
            + " INNER JOIN tbl_product product ON product.category_id = category.category_id"
            + " LEFT JOIN tbl_product_color color ON product.product_id = color.product_id"
            + " LEFT JOIN tbl_product_size size ON product.product_id = size.product_id"
            + " LEFT JOIN tbl_source_images image ON product.product_id = image.product_id"
            + " WHERE product.product_id = ?1 AND product.category_id = ?2"
            , nativeQuery = true)
    List<IOneProduct> findProductByKey(Long productId, Long categoryId);
}
