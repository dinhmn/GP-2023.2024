package com.graduationproject.backend.backendwebsiteshoe.dto;

/**
 * Article dto.
 *
 * @author Mai Ngoc Dinh
 */
public interface IArticleDTO {

  String getArticleId();

  String getProductId();

  String getProductName();

  String getArticleName();

  String getArticleDescription();

  String getArticleSeo();

  String getCreatedDate();

  String getUpdatedDate();

  String getArticleStatus();

  String getFileName();

  String getFileType();

  byte[] getFileData();
}
