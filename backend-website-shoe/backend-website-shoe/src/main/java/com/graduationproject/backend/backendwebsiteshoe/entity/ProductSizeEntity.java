package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(ProductSizeEntityKey.class)
@Table(name = "tbl_product_size")
public class ProductSizeEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public ProductSizeEntity() {

        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_size_id", nullable = false)
    private Long productSizeId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_size_name", nullable = false)
    private String productSizeName;
}
