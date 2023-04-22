package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.common.Constant;
import com.graduationproject.backend.backendwebsiteshoe.common.Image;
import com.graduationproject.backend.backendwebsiteshoe.entity.SourceImagesEntity;
import com.graduationproject.backend.backendwebsiteshoe.model.ProductModel;
import com.graduationproject.backend.backendwebsiteshoe.repository.SourceImagesRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Implement service of item source image.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class SourceImageService {

  private static final String TARGET_SOURCE = "D:/image/api-image";

  @Autowired
  CommonService commonService;

  @Autowired
  SourceImagesRepository sourceImagesRepository;

  /**
   * Insert or update image.
   *
   * @param productId    productId
   * @param files        files
   * @param productModel productModel
   * @param type         type
   * @throws Error with image.
   */
  public void insertOrUpdateImages(@NonNull Long productId, @NonNull List<MultipartFile> files,
                                   ProductModel productModel, String type) throws IOException {
    // Insert file image
    if (Constant.INSERT.equals(type)) {
      this.insertFileImage(files, productId, Constant.ZERO);
    }

    // Update file image
    if (Constant.UPDATE.equals(type)) {
      if (files.size() == productModel.getSourceImageModelList().size()) {
        this.updateFileImages(files, productId, productModel);
      }

      if (files.size() < productModel.getSourceImageModelList().size()) {
        // Delete image when file image in database > file image in input
        for (int i = files.size(); i < productModel.getSourceImageModelList().size(); i++) {
          Long imageId = Long.parseLong(productModel.getSourceImageModelList().get(i).getImageId());

          // Delete by primary key
          sourceImagesRepository.deleteById(imageId);
        }

        this.updateFileImages(files, productId, productModel);
      }

      if (files.size() > productModel.getSourceImageModelList().size()) {
        // Update file image when file image into database.
        this.updateFileImages(files, productId, productModel);

        // Insert new file image.
        this.insertFileImage(files, productId,
            productModel.getSourceImageModelList().size());
      }
    }

  }

  /**
   * Upload file article.
   *
   * @param file      file
   * @param articleId articleId
   * @param type      type
   * @throws Error with image.
   */
  @NonNull
  public void insertOrUpdate(MultipartFile file, Long articleId, String type) throws IOException {
    // Insert file image
    this.insertFileImage(file, articleId, type);
  }

  /**
   * Delete all source image of product by productId.
   *
   * @param productId  productId
   * @param imagesCode imagesCode
   */
  public List<SourceImagesEntity> selectByProductId(Long productId, List<String> imagesCode) {
    return sourceImagesRepository.selectByProductId(productId, imagesCode);
  }

  /**
   * Delete all source image of product by productId.
   *
   * @param productId  productId
   * @param imagesCode imagesCode
   */
  public void deleteByProductId(Long productId, List<String> imagesCode) {
    List<Long> imageIdList = this.selectByProductId(productId, imagesCode).stream()
            .map(SourceImagesEntity::getImageId)
            .toList();
    imageIdList.forEach(imageId -> sourceImagesRepository.deleteById(imageId));
  }

  /**
   * Select source image of article by articleId.
   *
   * @param articleId  articleId
   * @return entity
   */
  public Optional<SourceImagesEntity> selectImageIdByArticleId(Long articleId) {
    return sourceImagesRepository.findByArticleId(articleId);
  }

  /**
   * Delete all source image of article by articleId.
   *
   * @param articleId  articleId
   * @return True or False.
   */
  public Boolean deleteByArticleId(Long articleId) {
    Optional<SourceImagesEntity> entity = this.selectImageIdByArticleId(articleId);

    if (entity.isPresent()) {
      try {
        sourceImagesRepository.deleteById(entity.get().getImageId());
        return Boolean.TRUE;
      } catch (DataAccessException dataAccessException) {
        dataAccessException.printStackTrace();
      }
    }

    return Boolean.FALSE;
  }

  /**
   * To build source image use update.
   *
   * @param file          file
   * @param fileName      fileName
   * @param imageDatabase imageDatabase
   * @return entity
   */
  private SourceImagesEntity toBuildSourceImage(MultipartFile file, String fileName,
                                                SourceImagesEntity imageDatabase)
      throws IOException {
    SourceImagesEntity entity = new SourceImagesEntity();
    entity.setImageId(imageDatabase.getImageId());
    entity.setProductId(imageDatabase.getProductId());
    entity.setData(file.getBytes());
    entity.setFileType(file.getContentType());
    entity.setFileName(fileName);
    entity.setImageCode(imageDatabase.getImageCode());
    entity.setUserInformationId(imageDatabase.getUserInformationId());
    commonService.setCommonUpdateEntity(entity);

    return entity;
  }

  /**
   * To build source image use create new.
   *
   * @param file      file
   * @param fileName  fileName
   * @param imageCode imageCode
   * @param productId productId
   * @return entity
   */
  private SourceImagesEntity toBuildSourceImage(MultipartFile file, String fileName,
                                                String imageCode, Long productId)
      throws IOException {
    SourceImagesEntity entity = new SourceImagesEntity();
    entity.setProductId(productId);
    entity.setData(file.getBytes());
    entity.setFileType(file.getContentType());
    entity.setFileName(fileName);
    entity.setImageCode(imageCode);
    entity.setUserInformationId(null);
    commonService.setCommonCreatedEntity(entity);

    return entity;
  }

  /**
   * To build source image use create new with article.
   *
   * @param file      file
   * @param fileName  fileName
   * @param articleId articleId
   * @return entity
   */
  private SourceImagesEntity toBuildSourceImageArticle(MultipartFile file, String fileName,
                                                       Long articleId) throws IOException {
    SourceImagesEntity entity = new SourceImagesEntity();
    entity.setArticleId(articleId);
    entity.setData(file.getBytes());
    entity.setFileType(file.getContentType());
    entity.setFileName(fileName);
    entity.setImageCode(Image.IMAGE_ARTICLE.getCode());
    commonService.setCommonCreatedEntity(entity);

    return entity;
  }

  /**
   * To build source image use create new with article.
   *
   * @param entity   entity
   * @param file     file
   * @param fileName fileName
   * @return entity
   */
  private SourceImagesEntity toBuildSourceImageArticle(SourceImagesEntity entity,
                                                       MultipartFile file, String fileName)
      throws IOException {
    entity.setData(file.getBytes());
    entity.setFileType(file.getContentType());
    entity.setFileName(fileName);
    commonService.setCommonUpdateEntity(entity);

    return entity;
  }

  /**
   * Upload file article.
   *
   * @param file      file
   * @param articleId articleId
   * @throws Error with image.
   */
  private void insertFileImage(MultipartFile file, Long articleId, String type) throws IOException {
    String fileName =
        StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

    try {
      // Check file name
      if (fileName.contains("..")) {
        throw new IOException("Filename contains invalid path sequence" + fileName);
      }

      if (Constant.UPDATE.equals(type)) {
        Optional<SourceImagesEntity> sourceImagesEntity =
            sourceImagesRepository.findByArticleId(articleId);
        SourceImagesEntity entity;
        if (sourceImagesEntity.isPresent()) {
          entity = this.toBuildSourceImageArticle(sourceImagesEntity.get(), file, fileName);
        } else {
          entity =
              this.toBuildSourceImageArticle(file, fileName, articleId);
        }

        try {
          String pathPng = TARGET_SOURCE + Constant.SLASH + fileName;
          file.transferTo(new File(pathPng));
        } catch (FileSystemNotFoundException exception) {
          exception.printStackTrace();
        }

        sourceImagesRepository.save(entity);
      }

      if (Constant.INSERT.equals(type)) {
        // Insert data into database if type is insert.
        SourceImagesEntity entity =
            this.toBuildSourceImageArticle(file, fileName, articleId);

        try {
          String pathPng = TARGET_SOURCE + Constant.SLASH + fileName;
          file.transferTo(new File(pathPng));
        } catch (FileSystemNotFoundException exception) {
          exception.printStackTrace();
        }
        // Save data into database.
        sourceImagesRepository.save(entity);
      }

    } catch (Exception e) {
      throw new IOException("Could not save File: " + fileName);
    }
  }

  /**
   * Insert file.
   *
   * @param files      files
   * @param productId  productId
   * @param startIndex startIndex
   * @throws Error with image.
   */
  private void insertFileImage(List<MultipartFile> files, Long productId, int startIndex)
      throws IOException {
    for (int i = startIndex; i < files.size(); i++) {
      MultipartFile file = files.get(i);
      String fileName =
          StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
      try {
        // Check file name
        if (fileName.contains("..")) {
          throw new IOException("Filename contains invalid path sequence" + fileName);
        }

        // Insert data into database if type is insert.
        SourceImagesEntity entity =
            this.toBuildSourceImage(file, fileName, (i == 0) ? Image.IMAGE_MAIN_PRODUCT.getCode() :
                Image.IMAGE_SECONDARY_PRODUCT.getCode(), productId);

        try {
          String pathPng = TARGET_SOURCE + Constant.SLASH + fileName;
          file.transferTo(new File(pathPng));
        } catch (FileSystemNotFoundException exception) {
          exception.printStackTrace();
        }
        // Save data into database.
        sourceImagesRepository.save(entity);
      } catch (Exception e) {
        throw new IOException("Could not save File: " + fileName);
      }
    }
  }

  /**
   * Upload file.
   *
   * @param files        files
   * @param productId    productId
   * @param productModel productModel
   * @throws Error with image.
   */
  private void updateFileImages(List<MultipartFile> files, Long productId,
                                ProductModel productModel) throws IOException {
    for (int i = 0; i < files.size(); i++) {
      MultipartFile file = files.get(i);
      String fileName =
          StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
      try {
        // Check file name
        if (fileName.contains("..")) {
          throw new IOException("Filename contains invalid path sequence" + fileName);
        }

        // Update data into database if type is update
        Long imageId = Long.parseLong(productModel.getSourceImageModelList().get(i).getImageId());

        SourceImagesEntity entity;
        Optional<SourceImagesEntity> imageInDatabase =
            sourceImagesRepository.findById(imageId);
        if (imageInDatabase.isPresent()) {
          entity = this.toBuildSourceImage(file, fileName, imageInDatabase.get());
        } else {
          entity = this.toBuildSourceImage(file,
              fileName,
              (i == 0)
                  ? Image.IMAGE_MAIN_PRODUCT.getCode()
                  : Image.IMAGE_SECONDARY_PRODUCT.getCode(), productId);
        }
        // Save data into database.
        try {
          String pathPng = TARGET_SOURCE + Constant.SLASH + fileName;
          file.transferTo(new File(pathPng));
        } catch (FileSystemNotFoundException exception) {
          exception.printStackTrace();
        }
        sourceImagesRepository.save(entity);
      } catch (Exception e) {
        throw new IOException("Could not save File: " + fileName);
      }
    }
  }

}
