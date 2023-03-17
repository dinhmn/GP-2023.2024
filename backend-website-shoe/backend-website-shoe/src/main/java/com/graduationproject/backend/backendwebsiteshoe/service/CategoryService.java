package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntityKey;
import com.graduationproject.backend.backendwebsiteshoe.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Get all category.
     *
     * @return list of entity category.
     */
    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }

    /**
     * Insert new entity of category.
     *
     * @param categoryEntity categoryEntity
     * @return entity category.
     */
    public CategoryEntity insert(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    /**
     * Update category by primary key.
     *
     * @param categoryEntity categoryEntity
     * @param categoryId     categoryId
     * @param trademarkId    trademarkId
     * @return entity category.
     */
    public CategoryEntity update(CategoryEntity categoryEntity, Long categoryId, Long trademarkId) {
        CategoryEntityKey categoryEntityKey = new CategoryEntityKey();
        categoryEntityKey.setPk(categoryId, trademarkId);

        Optional<CategoryEntity> categoryInDatabase = categoryRepository.findById(categoryEntityKey);
        if (categoryInDatabase.isPresent()) {
            this.toBuildCategory(categoryEntity, categoryInDatabase.get());
            return categoryRepository.save(categoryInDatabase.get());
        }
        return categoryRepository.save(categoryEntity);
    }

    /**
     * Delete category by primary key.
     *
     * @param categoryId  categoryId
     * @param trademarkId trademarkId
     */
    public void delete(@NonNull Long categoryId, @NonNull Long trademarkId) {
        CategoryEntityKey categoryEntityKey = new CategoryEntityKey();
        categoryEntityKey.setPk(categoryId, trademarkId);
        categoryRepository.deleteById(categoryEntityKey);
    }

    /**
     * To build category
     *
     * @param currentEntity  entityInInput
     * @param databaseEntity entityInDatabase
     */
    private void toBuildCategory(CategoryEntity currentEntity, CategoryEntity databaseEntity) {
        databaseEntity.setCategoryName(currentEntity.getCategoryName());
        databaseEntity.setCategoryDescription(currentEntity.getCategoryDescription());
        databaseEntity.setSeo(currentEntity.getSeo());
        databaseEntity.setUpdatedDate(new Date());
        databaseEntity.setUpdatedBy(1);
        databaseEntity.setStatus(Boolean.TRUE);
    }
}
