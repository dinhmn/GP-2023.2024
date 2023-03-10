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


    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "product_price_sale")
    private BigDecimal productPriceSale;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "product_seo")
    private String productSeo;


}
