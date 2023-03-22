package com.graduationproject.backend.backendwebsiteshoe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {

    private Long categoryId;

    private Long trademarkId;

    private String trademarkName;

    private String categoryDescription;

    private String categoryName;

    private String categorySeo;

    private Date createdDate;

    private Date updatedDate;

    private String categoryStatus;
}
