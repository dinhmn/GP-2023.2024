package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(ProductEntityKey.class)
@Table(name = "tbl_product")
public class ProductEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public ProductEntity() {

        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Id
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_description", length = 2000)
    private String productDescription;

    @Column(name = "product_price", nullable = false)
    private BigDecimal productPrice;

    @Column(name = "product_price_sale")
    private BigDecimal productPriceSale;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "product_seo")
    private String productSeo;

}
