package com.graduationproject.backend.backendwebsiteshoe.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryForm {

    private Long trademarkId;

    private String categoryDescription;

    private String categoryName;

    private String categoryStatus;
}
