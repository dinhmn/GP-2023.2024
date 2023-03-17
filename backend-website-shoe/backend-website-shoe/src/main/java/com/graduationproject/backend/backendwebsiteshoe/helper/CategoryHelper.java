package com.graduationproject.backend.backendwebsiteshoe.helper;

import com.graduationproject.backend.backendwebsiteshoe.Common.Action;
import com.graduationproject.backend.backendwebsiteshoe.dto.ICategory;
import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntity;
import com.graduationproject.backend.backendwebsiteshoe.model.CategoryModel;
import com.graduationproject.backend.backendwebsiteshoe.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class CategoryHelper {

    @Autowired
    CategoryService categoryService;

    /**
     * Select all category.
     *
     * @return list category
     */
    public List<CategoryModel> getAllCategory() {
        return categoryService.getAllByTrademark().stream()
                .distinct()
                .map(this::convertCategory)
                .toList();
    }

    /**
     * Select all category.
     *
     * @return list category
     */
    public List<CategoryEntity> getAll() {
        return categoryService.getAll();
    }

    /**
     * Save or update category.
     *
     * @param categoryModel categoryModel
     * @param type          type
     * @return entity
     */
    public CategoryEntity saveOrInsert(CategoryModel categoryModel, String type) {
        CategoryEntity categoryEntity;
        if (Action.UPDATE.getValue().equals(type)) {
            Optional<CategoryEntity> category = categoryService.getCategoryByPrimaryKey(categoryModel.getCategoryId(), categoryModel.getTrademarkId());
            categoryEntity = this.toBuildCategory(categoryModel, Boolean.FALSE);
            BeanUtils.copyProperties(categoryEntity, category.get());
            return categoryService.update(categoryEntity, categoryModel.getCategoryId(), category.get().getTrademarkId());
        }
        categoryEntity = this.toBuildCategory(categoryModel, Boolean.TRUE);
        return categoryService.insert(categoryEntity);
    }

    /**
     * Delete category.
     *
     * @param categoryId  categoryId
     * @param trademarkId trademarkId
     * @return TRUE or FALSE
     */
    public Boolean deleteByPrimaryKey(Long categoryId, Long trademarkId) {
        Optional<CategoryEntity> category = categoryService.getCategoryByPrimaryKey(categoryId, trademarkId);
        if (category.isPresent()) {
            categoryService.delete(categoryId, trademarkId);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Build category.
     *
     * @param categoryModel categoryModel
     * @param isChecked     check
     * @return entity
     */
    private CategoryEntity toBuildCategory(CategoryModel categoryModel, Boolean isChecked) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(categoryModel.getCategoryId());
        categoryEntity.setCategoryName(categoryModel.getCategoryName());
        categoryEntity.setCategoryDescription(categoryModel.getCategoryDescription());
        categoryEntity.setSeo(categoryModel.getCategoryName().toLowerCase().replace(" ", "-"));
        categoryEntity.setTrademarkId(categoryModel.getTrademarkId());
        categoryEntity.setStatus(Boolean.TRUE);
        if (Boolean.TRUE.equals(isChecked)) {
            categoryEntity.setCreatedDate(new Date());
            categoryEntity.setCreatedBy(1);
            categoryEntity.setUpdatedDate(new Date());
            categoryEntity.setUpdatedBy(1);
        } else {
            categoryEntity.setUpdatedDate(categoryModel.getUpdatedDate());
            categoryEntity.setUpdatedBy(1);
        }
        return categoryEntity;
    }

    /**
     * Convert category entity => model.
     *
     * @param category category
     * @return category model.
     */
    private CategoryModel convertCategory(ICategory category) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setCategoryId(category.getCategoryId());
        categoryModel.setCategoryName(category.getCategoryName());
        categoryModel.setCategorySeo(category.getCategorySeo());
        categoryModel.setCategoryDescription(category.getCategoryDescription());
        categoryModel.setCategoryStatus(category.getCategoryStatus());
        categoryModel.setCreatedDate(category.getCreatedDate());
        categoryModel.setUpdatedDate(category.getUpdatedDate());
        categoryModel.setTrademarkId(category.getTrademarkId());
        categoryModel.setTrademarkName(category.getTrademarkName());
        return categoryModel;
    }
}
