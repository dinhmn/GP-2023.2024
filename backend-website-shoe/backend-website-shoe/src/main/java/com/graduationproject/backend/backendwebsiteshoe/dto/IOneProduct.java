package com.graduationproject.backend.backendwebsiteshoe.dto;

public interface IOneProduct {

  String getCategoryId();

  String getCategoryName();

  String getProductId();

  String getProductName();

  String getProductPrice();

  String getProductPriceSale();

  String getProductQuantity();

  String getProductDescription();

  String getProductStatus();

  String getProductSeo();

  String getProductColorId();

  String getProductColorName();

  String getProductColorQuantity();

  String getProductSizeId();

  String getProductSizeName();

  String getProductSizeQuantity();

  String getImageId();

  String getFileName();

  String getFileType();

  String getFileCode();

  byte[] getFileSize();
}
