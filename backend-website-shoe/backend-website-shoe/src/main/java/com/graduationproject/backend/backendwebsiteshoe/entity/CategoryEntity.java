package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(CategoryEntityKey.class)
@Table(name = "tbl_category")
public class CategoryEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public CategoryEntity() {

        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Id
    @Column(name = "trademark_id", nullable = false)
    private Long trademarkId;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_description", length = 3000,nullable = false)
    private String categoryDescription;

    @Column(name = "seo", nullable = false)
    private String seo;

}
