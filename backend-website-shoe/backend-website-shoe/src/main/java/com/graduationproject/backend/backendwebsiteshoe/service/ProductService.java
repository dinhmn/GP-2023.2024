package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.Common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.Common.Image;
import com.graduationproject.backend.backendwebsiteshoe.dto.IProduct;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductEntityKey;
import com.graduationproject.backend.backendwebsiteshoe.entity.SourceImagesEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.SourceImagesEntityKey;
import com.graduationproject.backend.backendwebsiteshoe.repository.ProductRepository;
import com.graduationproject.backend.backendwebsiteshoe.repository.SourceImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * Implement service of item product.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SourceImagesRepository sourceImagesRepository;

    @Autowired
    CommonService commonService;

    /**
     * Get all product.
     *
     * @return list of entity product.
     */
    public List<IProduct> getAll() {
        return productRepository.findAllProduct();
    }

    /**
     * Get product item.
     *
     * @return entity product.
     */
    public Optional<ProductEntity> getCategoryByPrimaryKey(Long categoryId, Long productId) {
        ProductEntityKey productEntityKey = new ProductEntityKey();
        productEntityKey.setPk(productId, categoryId);
        return productRepository.findById(productEntityKey);
    }

    /**
     * Insert new entity of product.
     *
     * @param productEntity productEntity
     * @return entity product.
     */
    public ProductEntity insert(ProductEntity productEntity, List<MultipartFile> files) throws IOException {
        ProductEntity entity = productRepository.save(productEntity);

        // Insert image into database
        this.insertOrUpdateImages(entity.getProductId(), files, 1L);

        return entity;
    }

    /**
     * Update product by primary key.
     *
     * @param productEntity productEntity
     * @param productId     productId
     * @param categoryId    categoryId
     * @return entity product.
     */
    public ProductEntity update(ProductEntity productEntity, Long productId, Long categoryId) {
        ProductEntityKey productEntityKey = new ProductEntityKey();
        productEntityKey.setPk(productId, categoryId);

        Optional<ProductEntity> productInDatabase = productRepository.findById(productEntityKey);
        if (productInDatabase.isPresent()) {
            this.toBuildProduct(productEntity, productInDatabase.get());
            return productRepository.save(productInDatabase.get());
        }
        return productRepository.save(productEntity);
    }

    /**
     * Delete category by primary key.
     *
     * @param productId  productId
     * @param categoryId categoryId
     */
    public void delete(@NonNull Long productId, @NonNull Long categoryId) {
        ProductEntityKey productEntityKey = new ProductEntityKey();
        productEntityKey.setPk(productId, categoryId);
        productRepository.deleteById(productEntityKey);
    }

    /**
     * Insert or update image.
     *
     * @param productId productId
     * @param files files
     * @param imageId imageId
     * @exception Error with image.
     */
    private void insertOrUpdateImages(@NonNull Long productId, @NonNull List<MultipartFile> files, long imageId) throws IOException {
        List<SourceImagesEntity> imageList = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            try {
                if (fileName.contains("..")) {
                    throw new IOException("Filename contains invalid path sequence" + fileName);
                }
                SourceImagesEntityKey keyImage = new SourceImagesEntityKey();
                keyImage.setPk(productId, imageId);
                Optional<SourceImagesEntity> imageInDatabase = sourceImagesRepository.findById(keyImage);
                SourceImagesEntity entity;
                if (imageInDatabase.isPresent()) {
                    entity = this.toBuildSourceImage(file, fileName, imageInDatabase.get());
                } else {
                    entity = this.toBuildSourceImage(file, fileName, (i == 0) ? Image.IMAGE_MAIN_PRODUCT.getCode() : Image.IMAGE_SECONDARY_PRODUCT.getCode(), productId);
                }

                imageList.add(entity);
            }  catch (Exception e) {
                throw new IOException("Could not save File: " + fileName);
            }
        }
        sourceImagesRepository.saveAll(imageList);
    }

    /**
     * To build category
     *
     * @param currentEntity  entityInInput
     * @param databaseEntity entityInDatabase
     */
    private void toBuildProduct(ProductEntity currentEntity, ProductEntity databaseEntity) {
//        databaseEntity.setCategoryName(currentEntity.getCategoryName());
//        databaseEntity.setCategoryDescription(currentEntity.getCategoryDescription());
//        databaseEntity.setSeo(currentEntity.getSeo());
//        databaseEntity.setUpdatedDate(new Date());
//        databaseEntity.setUpdatedBy(1);
//        databaseEntity.setStatus(Boolean.TRUE);
    }

    private SourceImagesEntity toBuildSourceImage(MultipartFile file, String fileName, SourceImagesEntity imageDatabase) throws IOException {
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

    private SourceImagesEntity toBuildSourceImage(MultipartFile file, String fileName, String imageCode, Long productId) throws IOException {
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
}
