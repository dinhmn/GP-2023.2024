package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.dto.ICategory;
import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntityKey;
import com.graduationproject.backend.backendwebsiteshoe.repository.CategoryRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Implement service of category.
 *
 * @author Mai Ngoc Dinh
 */
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
   * Get all category.
   *
   * @return list of entity category.
   */
  public List<ICategory> getAllByTrademark() {
    return categoryRepository.findAllCategory();
  }

  /**
   * Get all category.
   *
   * @return list of entity category.
   */
  public List<ICategory> getAllByTrademark(int pageNo, int pageSize, String searchValue,
                                           String sortBy) {
    return categoryRepository.findAllCategory(pageNo, pageSize, searchValue, sortBy);
  }

  /**
   * Get all category.
   *
   * @return list of entity category.
   */
  public Page<ICategory> getAllByTrademark(String searchValue, Pageable pageable) {
    return categoryRepository.findAllCategory(searchValue, pageable);
  }

  /**
   * Get all category.
   *
   * @return list of entity category.
   */
  public Optional<CategoryEntity> getCategoryByPrimaryKey(Long categoryId, Long trademarkId) {
    CategoryEntityKey categoryEntityKey = new CategoryEntityKey();
    categoryEntityKey.setCategoryId(categoryId);
    categoryEntityKey.setTrademarkId(trademarkId);
    return categoryRepository.findById(categoryEntityKey);
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
   * To build category.
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
