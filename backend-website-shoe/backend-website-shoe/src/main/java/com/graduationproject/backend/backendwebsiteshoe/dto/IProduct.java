package com.graduationproject.backend.backendwebsiteshoe.dto;

/**
 * Interface product.
 *
 * @author Mai Ngoc Dinh
 */
public interface IProduct {

  String getTrademarkName();

  String getCategoryId();

  String getCategoryName();

  String getProductId();

  String getProductName();

  String getProductPrice();

  String getProductPriceSale();

  String getProductQuantity();

  String getProductDescription();

  String getProductSeo();

  String getImageId();

  String getFileName();

  String getFileType();

  String getFileCode();

  byte[] getFileSize();
}
