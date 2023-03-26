package com.graduationproject.backend.backendwebsiteshoe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel {

    private Long categoryId;

    private Long productId;

    private String productName;

    private String productDescription;

    private String productPrice;

    private String productPriceSale;

    private Integer quantity;

    private String status;

    private List<SourceImageModel> sourceImageModelList;

    private List<ProductColorModel> productColorModelList;

    private List<ProductSizeModel> productSizeModelList;

}
