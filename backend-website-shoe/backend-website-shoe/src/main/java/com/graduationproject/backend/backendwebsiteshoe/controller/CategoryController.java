package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntity;
import com.graduationproject.backend.backendwebsiteshoe.forms.CategoryForm;
import com.graduationproject.backend.backendwebsiteshoe.helper.CategoryHelper;
import com.graduationproject.backend.backendwebsiteshoe.model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryHelper categoryHelper;

    /**
     * Get all category.
     *
     * @return list object.
     */
    @GetMapping(value = "/init")
    public List<CategoryModel> selectAllCategory() {
        return categoryHelper.getAllCategory();
    }

    /**
     * Get all category.
     *
     * @return list object.
     */
    @GetMapping(value = "/init/{trademarkId}/{categoryId}")
    public CategoryModel selectById(@PathVariable String categoryId, @PathVariable String trademarkId) {
        return categoryHelper.getById(Long.parseLong(categoryId), Long.parseLong(trademarkId));
    }

    /**
     * Register new category.
     *
     * @param categoryModel categoryModel
     * @return response entity
     */
    @PostMapping(value = "/register")
    public ResponseEntity<CategoryEntity> registerCategory(@RequestBody CategoryModel categoryModel) {
        CategoryEntity categoryEntity = categoryHelper.saveOrInsert(categoryModel, "insert");
        return new ResponseEntity<>(categoryEntity, HttpStatus.OK);
    }

    /**
     * Update category.
     *
     * @param categoryModel categoryModel
     * @param categoryId    categoryId
     * @param trademarkId   trademarkId
     * @return response entity
     */
    @PutMapping(value = "/update/{trademarkId}/{categoryId}")
    public ResponseEntity<CategoryEntity> updateCategory(@RequestBody CategoryModel categoryModel, @PathVariable final String categoryId, @PathVariable final String trademarkId) {
        categoryModel.setCategoryId(Long.parseLong(categoryId));
        categoryModel.setTrademarkId(Long.parseLong(trademarkId));
        CategoryEntity categoryEntity = categoryHelper.saveOrInsert(categoryModel, "update");

        return new ResponseEntity<>(categoryEntity, HttpStatus.OK);
    }

    /**
     * Delete category.
     *
     * @param categoryId  categoryId
     * @param trademarkId trademarkId
     * @return response entity 200.
     */
    @DeleteMapping(value = "/delete/{trademarkId}/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> updateCategory(@PathVariable String categoryId, @PathVariable String trademarkId) {

        Boolean action = categoryHelper.deleteByPrimaryKey(Long.parseLong(categoryId), Long.parseLong(trademarkId));
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", action);
        return ResponseEntity.ok(response);
    }
}
