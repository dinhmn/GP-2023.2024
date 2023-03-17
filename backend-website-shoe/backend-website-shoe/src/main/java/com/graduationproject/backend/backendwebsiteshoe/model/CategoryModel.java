package com.graduationproject.backend.backendwebsiteshoe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {

    private String categoryId;

    private String trademarkId;

    private String categoryDescription;

    private String categoryName;

    private String seo;
}
